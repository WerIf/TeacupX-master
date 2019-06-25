package com.lily.teacup.image_pool.cache.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

import java.lang.ref.SoftReference;

public class MemoryCacheUtils {
    /**
     * 因为在的版本中android 后台更加频繁的调用GC机制
     * 所以使用LruCache 用来存储已下载的bitmap
     * LruCache中使用强引用来缓存一定数量的值
     * 每当一个值被访问是，这个值就会被移动到队列的头部
     * 如果当插入数据时发现缓存不够使，就会将队列中访问次数最少的数据删掉
     * maxMemory 为设置的缓存大小
     */

    LruCache<String, SoftReference<Bitmap>> bitmapLruCache;

    public MemoryCacheUtils(){
        //设置图片总共占有的最大内存
        int maxMemory= (int) (Runtime.getRuntime().maxMemory()/8);
        bitmapLruCache=new LruCache<String,SoftReference<Bitmap>>(maxMemory);
    }



    public Bitmap acquire(String url){
      return bitmapLruCache.get(url).get();
    }

    public void saveFile(String url,Bitmap bitmap){
        bitmapLruCache.put(url,new SoftReference<>(bitmap));
    }
}
