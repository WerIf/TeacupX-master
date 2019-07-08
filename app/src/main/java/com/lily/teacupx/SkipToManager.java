package com.lily.teacupx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.lily.teacupx.ui.activity.GuideActivity;

/**
 * @author Lily
 * @date 2019/7/6 0006.
 * GitHub：https://github.com/JulyLily
 * email：228821309@qq.com
 * description：
 */
public class SkipToManager {

    public static final int GUIDE_CODE=101;

    public static void SkipToGuide(Context context){
        ((Activity) context).startActivityForResult(new Intent(context, GuideActivity.class),GUIDE_CODE);
    }
}
