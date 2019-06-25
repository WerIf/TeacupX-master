package com.lily.teacup.image_pool.load;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class HttpImageLoadUtils  {

    private static HttpImageLoadUtils INSTANCE;

    private HttpImageLoadUtils() {

    }

    public static HttpImageLoadUtils getInstance() {
        if (INSTANCE == null) {

            synchronized (HttpImageLoadUtils.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpImageLoadUtils();
                }
            }

        }
        return INSTANCE;
    }


    public Bitmap AsyncImageDownLoad(String loadUrl) {
        BufferedInputStream bf;

        try {
            URL url = new URL(loadUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);

            Log.e("TAG", "println result is:" + conn.getResponseCode());
            if (conn.getResponseCode() == 200) {
                bf = new BufferedInputStream(conn.getInputStream());
                Bitmap bitmap = BitmapFactory.decodeStream(bf);
                bf.close();
                return bitmap;
            }
            return null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;

    }


    /**
     * 同步返回
     *
     * @param loadUrl
     * @return
     */
    public Bitmap SyncImageDownLoad(String loadUrl) {


        FutureTask<Bitmap> task = new FutureTask<Bitmap>(new Callable<Bitmap>() {
            @Override
            public Bitmap call() throws Exception {


                BufferedInputStream bf;

                Bitmap bitmap;
                try {
                    URL url = new URL(loadUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true);

                    Log.e("TAG", "println result is:" + conn.getResponseCode());
                    if (conn.getResponseCode() == 200) {
                        bf = new BufferedInputStream(conn.getInputStream());
                        bitmap = BitmapFactory.decodeStream(bf);
                        bf.close();

                        return bitmap;

                    }

                    return null;
                } catch (MalformedURLException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();


                }

                return null;
            }
        });

        new Thread(task).start();

        try {
            return task.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


}
