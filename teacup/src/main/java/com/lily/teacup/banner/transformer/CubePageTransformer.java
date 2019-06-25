package com.lily.teacup.banner.transformer;


import android.view.View;

import androidx.core.view.ViewCompat;

public class CubePageTransformer extends TAPPageTransformer {
    private float mMaxRotation = 90.0f;

    public CubePageTransformer() {
    }

    public CubePageTransformer(float maxRotation) {
        setMaxRotation(maxRotation);
    }

    @Override
    public void handleInvisiblePage(View view, float position) {
        ViewCompat.setPivotX(view, view.getMeasuredWidth());
        ViewCompat.setPivotY(view, view.getMeasuredHeight() * 0.5f);
        ViewCompat.setRotationY(view, 0);
    }

    @Override
    public void handleLeftPage(View view, float position) {
        ViewCompat.setPivotX(view, view.getMeasuredWidth());
        ViewCompat.setPivotY(view, view.getMeasuredHeight() * 0.5f);
        ViewCompat.setRotationY(view, mMaxRotation * position);
    }

    @Override
    public void handleRightPage(View view, float position) {
        ViewCompat.setPivotX(view, 0);
        ViewCompat.setPivotY(view, view.getMeasuredHeight() * 0.5f);
        ViewCompat.setRotationY(view, mMaxRotation * position);
    }

    public void setMaxRotation(float maxRotation) {
        if (maxRotation >= 0.0f && maxRotation <= 90.0f) {
            mMaxRotation = maxRotation;
        }
    }

}
