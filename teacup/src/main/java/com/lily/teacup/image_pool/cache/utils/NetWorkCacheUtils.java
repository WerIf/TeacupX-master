package com.lily.teacup.image_pool.cache.utils;

import android.graphics.Bitmap;

import com.lily.teacup.image_pool.load.HttpImageLoadUtils;

public class NetWorkCacheUtils {
    private LocalCacheUtils mLocalCacheUtils;
    private MemoryCacheUtils mMemoryCacheUtils;

    public NetWorkCacheUtils( MemoryCacheUtils memoryCacheUtils,LocalCacheUtils localCacheUtils){
        this.mMemoryCacheUtils=memoryCacheUtils;
            this.mLocalCacheUtils=localCacheUtils;

    }
    public Bitmap acquire(String url){
        HttpImageLoadUtils loadUtils= HttpImageLoadUtils.getInstance();
        Bitmap bitmap=loadUtils.SyncImageDownLoad(url);

        mLocalCacheUtils.saveFile(url,bitmap);
        mMemoryCacheUtils.saveFile(url,bitmap);
        return bitmap;
    }
}
