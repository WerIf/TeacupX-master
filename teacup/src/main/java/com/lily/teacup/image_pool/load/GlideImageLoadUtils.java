package com.lily.teacup.image_pool.load;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


public class GlideImageLoadUtils implements GlideImageLoadInterface {

    private static GlideImageLoadUtils INSTANCE;

    private GlideImageLoadUtils(){

    }

    public static GlideImageLoadUtils getInstance(){
        if (INSTANCE == null) {

            synchronized (GlideImageLoadUtils.class) {
                if (INSTANCE == null) {
                    INSTANCE = new GlideImageLoadUtils();
                }
            }

        }
        return INSTANCE;
    }



        @Override
    public void ImageLoad(Context mContext, ImageView mImageView, String url) {
        Glide.with(mContext)
                .load(url)
                .into(mImageView);
    }

    @Override
    public void ImageLoad(Context mContext, ImageView mImageView, String url, int mErrorPath,ImgUtilsType loadType) {

        RequestOptions mOptions=new RequestOptions();
        switch (loadType.getValue()){
            case 0:
                mOptions.error(mErrorPath);
                break;
            case 1:
                mOptions.placeholder(mErrorPath);
                break;
        }

        Glide.with(mContext)
                .load(url)
                .apply(mOptions)
                .into(mImageView);
    }

    @Override
    public void ImageLoad(Context mContext, ImageView mImageView, String url, int mErrorPath, int placeHolderPath) {

        RequestOptions mOptions=new RequestOptions()
                .error(mErrorPath)
                .placeholder(placeHolderPath);

        Glide.with(mContext)
                .load(url)
                .apply(mOptions)
                .into(mImageView);
    }


}
