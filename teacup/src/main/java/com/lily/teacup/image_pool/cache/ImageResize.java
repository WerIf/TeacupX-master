package com.lily.teacup.image_pool.cache;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import java.io.ByteArrayOutputStream;

public class ImageResize {

    private static String TAG=ImageResize.class.getName();

    /**
     *  缩放bitmap
     * @param context
     * @param id
     * @param maxW
     * @param maxH
     * @param hasAlpha
     * @param reusable
     * @return
     */
    public static Bitmap resizeBitmap(Context context, Bitmap id, int maxW, int maxH, boolean hasAlpha, Bitmap reusable) {
        Resources resources = context.getResources();
        BitmapFactory.Options mOptions = new BitmapFactory.Options();
        mOptions.inJustDecodeBounds = true;
//        BitmapFactory.decodeResource(resources, id, mOptions);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        id.compress(Bitmap.CompressFormat.PNG,100,baos);
        BitmapFactory.decodeByteArray(baos.toByteArray(),0,baos.toByteArray().length,mOptions);
        //根据宽，高进行缩放
        int w = mOptions.outWidth;
        int h = mOptions.outHeight;

        Log.i(TAG, "resizeBitmap: "+w+" "+h);
        //设置缩放系数
        mOptions.inSampleSize = calculateInSampleSize(w, h, maxW, maxH);

        if(!hasAlpha){
            mOptions.inPreferredConfig= Bitmap.Config.RGB_565;
        }

        mOptions.inJustDecodeBounds=false;
        //设置可以复用，开启异变
        mOptions.inMutable=true;
        mOptions.inBitmap=reusable;

//        return BitmapFactory.decodeResource(resources,id,mOptions);
        return BitmapFactory.decodeByteArray(baos.toByteArray(),0,baos.toByteArray().length,mOptions);

    }

    /**
     * 计算缩放比例
     *
     * @param w
     * @param h
     * @param maxW
     * @param maxH
     * @return
     */
    private static int calculateInSampleSize(int w, int h, int maxW, int maxH) {
        int inSampleSize = 1;
        if (w > maxW && h > maxH) {


            do {
                inSampleSize *= 2;
            } while (w/inSampleSize> maxW && h/inSampleSize>maxH);

        }

        return inSampleSize;
    }

    private static int getSize(Bitmap bitmap){
        if(Build.VERSION.SDK_INT>12){
            return bitmap.getByteCount();
        }else{
            return bitmap.getRowBytes()*bitmap.getHeight();
        }
    }
}
