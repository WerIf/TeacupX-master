package com.lily.teacupx.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.lily.teacup.banner.TAPBanner;
import com.lily.teacup.banner.TAPLocalImageSize;
import com.lily.teacup.basicclass.BaseActivity;
import com.lily.teacup.fragment_bridge.BridgeManager;
import com.lily.teacup.tools.FullWindows;
import com.lily.teacupx.MainActivity;
import com.lily.teacupx.R;
import com.lily.teacupx.SkipToManager;

public class GuideActivity extends BaseActivity {
    private TAPBanner mBackgroundBanner;
    private TAPBanner mForegroundBanner;

    @Override
    public void beforeOnCreate() {

        FullWindows.screenWindows(this);
    }


    @Override
    public int getContentViewResId() {
        return R.layout.activity_guide;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        initView();
        setListener();
        processLogic();
    }

    @Override
    public BridgeManager backBaseBridge(BridgeManager bridgeManager) {
        return null;
    }


    private void initView() {
        mBackgroundBanner = findViewById(R.id.banner_guide_background);
        mForegroundBanner = findViewById(R.id.banner_guide_foreground);
    }

    private void setListener() {
        /**
         * 设置进入按钮和跳过按钮控件资源 id 及其点击事件
         * 如果进入按钮和跳过按钮有一个不存在的话就传 0
         * 在 BGABanner 里已经帮开发者处理了防止重复点击事件
         * 在 BGABanner 里已经帮开发者处理了「跳过按钮」和「进入按钮」的显示与隐藏
         */
        mForegroundBanner.setEnterSkipViewIdAndDelegate(R.id.btn_guide_enter, R.id.tv_guide_skip, new TAPBanner.GuideDelegate() {
            @Override
            public void onClickEnterOrSkip() {
//                startActivity(new Intent(GuideActivity.this, MainActivity.class));
//                finish();

                setResult(SkipToManager.GUIDE_CODE,new Intent());
                finish();
            }
        });
    }

    private void processLogic() {
        // Bitmap 的宽高在 maxWidth maxHeight 和 minWidth minHeight 之间
        TAPLocalImageSize localImageSize = new TAPLocalImageSize(720, 1280, 320, 640);
        // 设置数据源
        mBackgroundBanner.setData(localImageSize, ImageView.ScaleType.CENTER_CROP,
                R.mipmap.one,
                R.mipmap.three,
                R.mipmap.six);

        mForegroundBanner.setData(localImageSize, ImageView.ScaleType.CENTER_CROP,
                R.mipmap.one,
                R.mipmap.three,
                R.mipmap.six);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 如果开发者的引导页主题是透明的，需要在界面可见时给背景 Banner 设置一个白色背景，避免滑动过程中两个 Banner 都设置透明度后能看到 Launcher
        mBackgroundBanner.setBackgroundResource(android.R.color.white);
    }
}
