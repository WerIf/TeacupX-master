package com.lily.teacupx;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;

import com.google.android.material.tabs.TabLayout;
import com.lily.teacup.annotation.autoknife.FindView;
import com.lily.teacup.basicclass.BaseActivity;
import com.lily.teacup.basicclass.BaseFragment;
import com.lily.teacup.basicclass.ToolBarOptions;
import com.lily.teacup.fragment_bridge.BridgeManager;
import com.lily.teacupx.adapter.MainPageAdapter;
import com.lily.teacupx.ui.fragment.FuFragment;
import com.lily.teacupx.ui.fragment.OnFragment;
import com.lily.teacupx.ui.fragment.ThFragment;
import com.lily.teacupx.ui.fragment.TwFragment;

import java.util.List;


public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private String[] tabStr=new String[]{"壹","贰","叁","肆"};

    private BaseFragment[] fragmentList=new BaseFragment[]{
            new OnFragment(),
            new TwFragment(),
            new ThFragment(),
            new FuFragment()
    };

    @FindView(R.id.mainTab)
    TabLayout mainTab;

    @FindView(R.id.mainPage)
    ViewPager mainPage;

    @Override
    public void beforeOnCreate() {

    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        SkipToManager.SkipToGuide(this);
    }

    @Override
    public BridgeManager backBaseBridge(BridgeManager bridgeManager) {
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(null==data)return;

        switch (requestCode){
            case SkipToManager.GUIDE_CODE:

                initMainPage();
                break;
        }

    }

    private void initMainPage() {

        ToolBarOptions options=new ToolBarOptions();
        options.titleString=getString(R.string.app_name);
        options.logoId=R.mipmap.tp_icon;
        options.titleColor=R.color.white;
        setToolbar(R.id.mainBar,options);

        MainPageAdapter adapter=new MainPageAdapter(getSupportFragmentManager(),fragmentList,tabStr);

        mainPage.addOnPageChangeListener(this);

        mainPage.setAdapter(adapter);

        mainTab.setupWithViewPager(mainPage);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
