package com.lily.teacup.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.VelocityTrackerCompat;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TAPViewPager extends ViewPager {
    private boolean mAllouUserScrollable;
    private AutoPlayDelegate mAutoPlayDelegate;

    public TAPViewPager(@NonNull Context context) {
        super(context);
    }

    public TAPViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置移动动画
     * @param reverseDrawingOrder
     * @param transformer
     */
    @Override
    public void setPageTransformer(boolean reverseDrawingOrder, @Nullable PageTransformer transformer) {
        super.setPageTransformer(reverseDrawingOrder, transformer);
        /**
         * 重写setPageTransformer方法，移除版本限制，通过反射设置方法和参数
         */

        Class viewpagerClass=ViewPager.class;

        try {
            boolean hasTransformer=transformer!=null;

            //获取 mPageTransformer 属性
            Field pageTransformerField = viewpagerClass.getDeclaredField("mPageTransformer");
            pageTransformerField.setAccessible(true);
            PageTransformer mPageTransformer = (PageTransformer) pageTransformerField.get(this);

            boolean needsPopulate=hasTransformer!=(mPageTransformer!=null);
            //设置动画
            pageTransformerField.set(this,transformer);

            Method setChildrenDrawingOrderEnabledCompatMethod=viewpagerClass.getDeclaredMethod("setChildrenDrawingOrderEnabledCompatMethod",boolean.class);
            setChildrenDrawingOrderEnabledCompatMethod.setAccessible(true);
            setChildrenDrawingOrderEnabledCompatMethod.invoke(this,hasTransformer);

            Field drawingOrderField=viewpagerClass.getDeclaredField("mDrawingOrder");
            drawingOrderField.setAccessible(true);

            if(hasTransformer){
                drawingOrderField.setInt(this,reverseDrawingOrder ? 2 : 1);
            }else{
                drawingOrderField.setInt(this, 0);
            }

            if(needsPopulate){
                Method populateMethod=viewpagerClass.getDeclaredMethod("populate");
                populateMethod.setAccessible(true);
                populateMethod.invoke(this);
            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    /**
     * 设置调用setCurrentItem(int item, boolean smoothScroll)方法时，page切换的时间长度
     *
     * @param duration page切换时间
     */
    public void setPageChangeDuration(int duration){
        try {
            Field scrollerField=ViewPager.class.getDeclaredField("mScroller");
            scrollerField.setAccessible(true);
            scrollerField.set(this,new TAPBannerScroller(getContext(),duration));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 切换到指定索引的页面，主要用于自动轮播
     * @param position
     * @param smoothScroll
     */
    public void setBannerCurrentItemInternal(int position, boolean smoothScroll){
        Class viewpagerClass=ViewPager.class;
        try {
            Method setCurrentItemInternalMethod=viewpagerClass.getDeclaredMethod("setCurrentItemInternal", int.class, boolean.class, boolean.class);
            setCurrentItemInternalMethod.setAccessible(true);
            setCurrentItemInternalMethod.invoke(this,position, smoothScroll, true);
            ViewCompat.postInvalidateOnAnimation(this);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     *  设置是否允许用户手指滑动
     * @param allowUserScrollable
     */
    public void setAllowUserScrollable(boolean allowUserScrollable){
        mAllouUserScrollable = allowUserScrollable;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(mAllouUserScrollable){
            return super.onInterceptTouchEvent(ev);
        }else{
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mAllouUserScrollable){
            if (mAutoPlayDelegate != null &&
                    (ev.getAction() == MotionEvent.ACTION_CANCEL ||
                            ev.getAction() == MotionEvent.ACTION_UP)) {
                mAutoPlayDelegate.handleAutoPlayActionUpOrCancel(getXVelocity());
                return false;
            } else {
                return super.onTouchEvent(ev);
            }
        }
        return false;
    }

    private float getXVelocity(){
        float xVelocity=0;
        Class viewpagerClass=ViewPager.class;
        try {
            Field velocityTrackerField=viewpagerClass.getDeclaredField("mVelocityTracker");
            velocityTrackerField.setAccessible(true);
            VelocityTracker velocityTracker= (VelocityTracker) velocityTrackerField.get(this);

            Field activePointerIdField=viewpagerClass.getDeclaredField("mActivePointerId");
            activePointerIdField.setAccessible(true);

            Field maximumVelocityField=viewpagerClass.getDeclaredField("mMaximumVelocity");
            maximumVelocityField.setAccessible(true);
            int maximumVelocity= maximumVelocityField.getInt(this);

            velocityTracker.computeCurrentVelocity(1000,maximumVelocity);
            xVelocity=VelocityTrackerCompat.getXVelocity(velocityTracker,activePointerIdField.getInt(this));

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return xVelocity;
    }

    public void setAutoPlayDelegate(AutoPlayDelegate mAutoPlayDelegate) {
        this.mAutoPlayDelegate = mAutoPlayDelegate;
    }

    public interface AutoPlayDelegate {
        void handleAutoPlayActionUpOrCancel(float xVelocity);
    }
}
