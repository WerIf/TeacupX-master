package com.lily.teacup.image_pool.cache.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class LocalCacheUtils {


    private static final String CACHE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/caches";


    /**
     * 获取bitmap
     *
     * @param url
     * @return
     */
    public Bitmap acquire(String url) {
        String fileName = null;//把图片的url当做文件名,并进行MD5加密
        try {
            File file = new File(CACHE_PATH, fileName);

            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));

            return bitmap;
        } catch (Exception e) {

        }
        return null;
    }

    /**
     *  保存bitmap
     * @param url
     * @param bitmap
     * @return
     */
    public boolean saveFile(String url, Bitmap bitmap) {

        try {
            File file = new File(CACHE_PATH, url);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }

            return bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }
}
