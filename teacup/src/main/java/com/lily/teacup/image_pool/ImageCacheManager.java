package com.lily.teacup.image_pool;

import android.graphics.Bitmap;

import com.lily.teacup.image_pool.cache.utils.LocalCacheUtils;
import com.lily.teacup.image_pool.cache.utils.MemoryCacheUtils;
import com.lily.teacup.image_pool.cache.utils.NetWorkCacheUtils;

public class ImageCacheManager {

    static ImageCacheManager imageCacheManager;

    MemoryCacheUtils memoryCacheUtils;
    LocalCacheUtils localCacheUtils;
    NetWorkCacheUtils netWorkCacheUtils;

    private ImageCacheManager(){
        memoryCacheUtils=new MemoryCacheUtils();
        localCacheUtils=new LocalCacheUtils();
        netWorkCacheUtils=new NetWorkCacheUtils(memoryCacheUtils,localCacheUtils);
    }

    public static ImageCacheManager getInstance(){

        if(imageCacheManager==null){
            synchronized (imageCacheManager){
                if(imageCacheManager==null){
                    imageCacheManager=new ImageCacheManager();
                }
            }
        }

        return imageCacheManager;
    }


    public Bitmap acquire(String url){

        Bitmap bitmap=null;
        bitmap=memoryCacheUtils.acquire(url);

        if(bitmap!=null)return bitmap;

        bitmap=localCacheUtils.acquire(url);

        if(bitmap!=null)return bitmap;

        return netWorkCacheUtils.acquire(url);
    }
}
