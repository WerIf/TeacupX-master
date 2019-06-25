package com.lily.teacup.tools;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 *  自定义Handler
 * @param <T>
 */
public class WiHandler<T extends Activity> extends Handler {

    private WeakReference<Activity> activityWeakReference;

    private Class t;

    public WiHandler(Activity activity, Class<T> type) {
        activityWeakReference = new WeakReference<>(activity);

        t = type;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);

        switch (msg.what) {
            case WiHandlerMenu.DELAY_JUMP:
                activityWeakReference.get().startActivity(new Intent(activityWeakReference.get(), t));
                break;
            case WiHandlerMenu.DELAY_LOGIN:
                activityWeakReference.get().startActivity(new Intent(activityWeakReference.get(), t));
                break;
        }
    }

}
