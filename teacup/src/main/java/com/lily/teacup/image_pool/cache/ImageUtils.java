package com.lily.teacup.image_pool.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.lily.teacup.image_pool.load.HttpImageLoadUtils;


public class ImageUtils {

    /**
     *  下载图片 并将图片保存在对象池中
     *  实现复用图片资源
     * @param context
     * @param url       图片路径
     * @param wPixels   图片目标宽度
     * @param hPixels   图片目标高度
     * @param imageView 图片设置对象
     */
    public static void load(Context context, String url, int wPixels, int hPixels, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            return;
        }

        String key = Generate(url);

        Bitmap bitmap = ImageCache.getInstance().getBitmapFormMemory(key);
        if (null == bitmap) {

            //如果内存没数据，就去复用池找
            Bitmap reusable = ImageCache.getInstance().getReusable(wPixels, hPixels, 1);
            //reuseable能复用的内存
            //从磁盘找
            bitmap = ImageCache.getInstance().getBitmapFromDisk(key, reusable);
            //如果磁盘中也没缓存,就从网络下载
            if (null == bitmap) {
                //压缩图片
//
                Bitmap resource = HttpImageLoadUtils.getInstance().SyncImageDownLoad(url);

                bitmap = ImageResize.resizeBitmap(context, resource, wPixels, hPixels, false, reusable);

                imageView.setImageBitmap(bitmap);

                ImageCache.getInstance().putBitmapToMemory(key, resource);

                ImageCache.getInstance().putBitmapToDisk(key, resource);

                Log.i("jett", "从网络加载了数据");
            } else {
                imageView.setImageBitmap(bitmap);
                Log.i("jett", "从磁盘中加载了数据");
            }
        } else {
            imageView.setImageBitmap(bitmap);
            Log.i("jett", "从缓存中加载了数据");
        }
    }

    /**
     * 通过path创建对应的key值 并将key和path绑定 存入数据库
     *
     * @param context
     * @param path
     * @return
     */
    private static String createKey(Context context, String path) {
        long currentTime = System.currentTimeMillis();
//        DiskLruCacheSQLBean bean = new DiskLruCacheSQLBean();
//        bean.setPath(path);
//        bean.setUid(currentTime);
//        ImilyApplication.getApplication().getDaoInstant().getDiskLruCacheSQLBeanDao().insert(bean);
        return currentTime + "";
    }

    /**
     * 通过path查找对应的key值
     *
     * @param path
     * @return
     */
    private static String pathFormKey(String path) {
//        DiskLruCacheSQLBean item = ImilyApplication.getApplication().getDaoInstant().getDiskLruCacheSQLBeanDao().queryBuilder().where(DiskLruCacheSQLBeanDao.Properties.Path.eq(path)).unique();
//
//        if (item == null) {
            return null;
//        }
//
//        return item.getUid() + "";
    }

    private static StringBuilder builder=new StringBuilder();

    /**
     *  获取图片资源保存的key
     *      获取图片路径的最后10位 并将10位字符通过ASCLL码表转换成内容位数字的字符串
     * @param path
     * @return
     */
    public static String Generate(String path){

        if(builder!=null){
            builder.setLength(0);
        }

        String itemString=path.substring(path.length()-10,path.length());
        char[] items=itemString.toCharArray();

        for (char item : items) {
            byte by= (byte) item;
            builder.append(""+by);
        }

        return builder.toString();
    }
}
