package com.lily.teacup.basicclass;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.lily.teacup.annotation.autoknife.FindKnifeProcess;
import com.lily.teacup.annotation.autowired.AutoWriedProcess;
import com.lily.teacup.fragment_bridge.BridgeManager;
import com.lily.teacup.tools.UnitConversionUtils;


import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;



public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;
    protected int screenWidth;

    public abstract void beforeOnCreate();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        screenWidth = UnitConversionUtils.getScreenWidth(this) / 2;

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        Transition slide_left = TransitionInflater.from(this).inflateTransition(android.R.transition.slide_left);
        //使用动画时机
        getWindow().setExitTransition(slide_left);
        beforeOnCreate();
        this.setContentView(this.getContentViewResId());
        FindKnifeProcess.bind(this);
        AutoWriedProcess.bind(this);
        init(savedInstanceState);


    }

    public abstract int getContentViewResId();

    protected void setToolbar(int toolbarId, int titleId, int logoId) {
        toolbar = findViewById(toolbarId);
        toolbar.setLogo(logoId);
        toolbar.setTitle(titleId);
        setSupportActionBar(toolbar);
    }

    protected void setToolbar(int toolbarId,boolean setBackIcon){
        if(setBackIcon){
            toolbar = findViewById(toolbarId);
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    protected void setToolbar(int toolbarId, ToolBarOptions options) {
        toolbar = findViewById(toolbarId);
        if (options.titleId != 0) {
            toolbar.setTitle(options.titleId);
        }

        if (!TextUtils.isEmpty(options.titleString)) {
            toolbar.setTitle(options.titleString);
        }

        if (options.logoId != 0) {
            toolbar.setLogo(options.logoId);
        }

        if (options.isNeedNavigate) {

            toolbar.setNavigationIcon(options.navigateId);
            toolbar.setContentInsetStartWithNavigation(0);
            toolbar.setNavigationOnClickListener(v ->
                    onNavigateUpClick());
        }
    }

    private void onNavigateUpClick() {
        onBackPressed();
    }

    /**
     * 页面初始化
     *
     * @param savedInstanceState
     */
    public abstract void init(Bundle savedInstanceState);



    public <T extends View> T findClickView(int id) {

        T view = (T) findViewById(id);
        view.setOnClickListener(new EventListener(this));
        return view;
    }

    /**
     * Fragment和Activity交互传递数据
     *
     * @param tag
     * @param c
     * @param <TargetFragment>
     */
    public <TargetFragment extends BaseFragment> void setFunctionForFragment(String tag, TargetFragment c) {


        BridgeManager bridgeManager = BridgeManager.getInstance();

        Log.e("BaseActivity", "judge bridgeManager is null :" + (bridgeManager == null) + "  name:" + c.getClass().getName());

        c.setBridgeManager(backBaseBridge(bridgeManager));
    }

    /**
     * 数据传递的具体实现对象
     *
     * @param bridgeManager
     * @return
     */
    public abstract BridgeManager backBaseBridge(BridgeManager bridgeManager);


    /**
     *  Toolbar 设置返回按钮 对应监听返回事件
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
