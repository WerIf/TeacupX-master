package com.lily.teacup.image_pool.cache;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.LruCache;


import com.lily.teacup.BuildConfig;
import com.lily.teacup.image_pool.cache.disk.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ImageCache {
    private static ImageCache instance;
    private Context mContext;
    private LruCache<String, Bitmap> memoryCache;
    private DiskLruCache diskLruCache;

    private BitmapFactory.Options mOptions = new BitmapFactory.Options();

    /**
     * 引用队列
     */
    private ReferenceQueue referenceQueue;
    /**
     * 队列轮询线程
     */
    private Thread clearReferenceQueue;
    /**
     * 定义复用池
     */

    private boolean shutDown;

    public static Set<WeakReference<Bitmap>> reusablePool;

    private ImageCache() {

    }

    public static ImageCache getInstance() {
        if (null == instance) {
            synchronized (ImageCache.class) {
                if (null == instance) {
                    instance = new ImageCache();
                }
            }
        }
        return instance;
    }

    private ReferenceQueue<Bitmap> getReferenceQueue() {
        if (null == referenceQueue) {
            //当弱引用需要被回收的时候，会进入这个队列
            referenceQueue = new ReferenceQueue();

            clearReferenceQueue = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!shutDown) {
                        try {
                            //此处使用remove方法，remove方法具有线程阻塞功能
                            Reference<Bitmap> reference = referenceQueue.remove();

                            Bitmap bitmap = reference.get();

                            if (null != bitmap && !bitmap.isRecycled()) {
                                //将bitmap所指的内存块释放掉
                                bitmap.recycle();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            clearReferenceQueue.start();
        }

        return referenceQueue;
    }


    /**
     * @param context
     * @param dir     存放图片文件的路径
     */
    public void init(Context context, String dir) {

        this.mContext = context.getApplicationContext();

        //复用池
        //HashSet ArrayList HashMap都是线程不安全的
        //使用Collections.synchronized xx 生成同步对象
        reusablePool = Collections.synchronizedSet(new HashSet<>());

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //获取程序最大可用内存 单位是M
        int memoryClass = am.getMemoryClass();
        //设置LruCache的内存最大值 单位是byte
        memoryCache = new LruCache<String, Bitmap>(memoryClass / 8 * 1024 * 1024) {

            /**
             * @param key
             * @param value
             * @return value    占用的内存的大小
             */
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //19之后 复用bitmap内存块必须小于或等于分配内存块 inSampleSize >= 1
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {

                    return value.getAllocationByteCount();
                }
                //19之前 必须同等大小，才能复用 inSampleSize = 1
                return value.getByteCount();
            }

            /**
             *  当lru中满了，bitmap从lru中移除对象时，回调以下这个方法
             * @param evicted
             * @param key
             * @param oldValue
             * @param newValue
             */
            @Override
            protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
//                super.entryRemoved(evicted, key, oldValue, newValue);

                if (oldValue.isMutable()) {
                    /**
                     * oldValue开启异变 将oldValue添加入复用池
                     */
                    reusablePool.add(new WeakReference<>(oldValue));
                } else {
                    /**
                     * 将oldValue释放掉
                     */
                    oldValue.recycle();
                }

            }
        };

        try {
            /**
             * @param directory 将文件写入磁盘中
             * @param versionCode 版本号
             * @param valueCount 标识一个key对应valueCount个文件
             * @param maxSize 设置最大存储值
             */
            diskLruCache = DiskLruCache.open(new File(dir), BuildConfig.VERSION_CODE, 1, 10 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }

        getReferenceQueue();

    }


    public void putBitmapToMemory(String key, Bitmap bitmap) {
        memoryCache.put(key, bitmap);
    }

    public Bitmap getBitmapFormMemory(String key) {
        return memoryCache.get(key);
    }

    public void clearMemoryCache() {
        memoryCache.evictAll();
    }

    /**
     * 获取复用池中的内容
     *
     * @param w
     * @param h
     * @param inSampleSize
     * @return
     */
    public Bitmap getReusable(int w, int h, int inSampleSize) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return null;
        }

        Bitmap reusable = null;

        Iterator<WeakReference<Bitmap>> iterator = reusablePool.iterator();

        while (iterator.hasNext()) {
            Bitmap bitmap = iterator.next().get();

            if (null != bitmap) {

                if (checkInBitmap(bitmap, w, h, inSampleSize)) {

                    reusable = bitmap;

                    iterator.remove();
                    break;
                } else {
                    iterator.remove();
                }
            }
        }

        return reusable;
    }

    /**
     * 判断bitmap内存块当前是否可以复用
     *
     * @param bitmap
     * @param w
     * @param h
     * @param inSampleSize
     * @return
     */
    private boolean checkInBitmap(Bitmap bitmap, int w, int h, int inSampleSize) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return bitmap.getWidth() == w && bitmap.getHeight() == h && inSampleSize == 1;
        }

        if (inSampleSize >= 1) {
            w /= inSampleSize;
            h /= inSampleSize;
        }

        int byteCount = w * h * getPixelsCount(bitmap.getConfig());

        //判断bitmap所占内存是否小于等于bitmap所指的内存块
        return byteCount < bitmap.getAllocationByteCount();
    }

    /**
     * 计算bitmap每一个像素点所占的字节
     *
     * @param config
     * @return
     */
    private int getPixelsCount(Bitmap.Config config) {

        if (config == Bitmap.Config.ARGB_8888) {
            return 4;
        }

        return 2;
    }

    public void putBitmapToDisk(String key, Bitmap bitmap) {
        DiskLruCache.Snapshot snapshot = null;
        OutputStream os = null;
        try {
            snapshot = diskLruCache.get(key);
            //如果缓存中存在这个文件 不处理
            if (null == snapshot) {
                DiskLruCache.Editor editor = diskLruCache.edit(key);

                if (null != editor) {
                    //设置流的偏移量 如果从头开始写 设置为0
                    os=editor.newOutputStream(0);

                    bitmap.compress(Bitmap.CompressFormat.JPEG,50,os);
                    editor.commit();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null!=snapshot){
                snapshot.close();
            }

            if(null!=os){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Bitmap getBitmapFromDisk(String key,Bitmap reusable){
        DiskLruCache.Snapshot snapshot=null;
        Bitmap bitmap=null;
        try {
            snapshot=diskLruCache.get(key);
            if(null==snapshot){
                return null;
            }
            //获取文件输入流 读取bitmap
            InputStream is=snapshot.getInputStream(0);
            //解码图片 写入
            mOptions.inMutable=true;
            mOptions.inBitmap=reusable;
            bitmap=BitmapFactory.decodeStream(is);

            if(null!=bitmap){
                memoryCache.put(key,bitmap);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null!=snapshot){
                snapshot.close();
            }
        }

        return bitmap;
    }


}
