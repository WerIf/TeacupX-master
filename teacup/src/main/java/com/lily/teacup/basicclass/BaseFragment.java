package com.lily.teacup.basicclass;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lily.teacup.annotation.autoknife.FindKnifeProcess;
import com.lily.teacup.annotation.autowired.AutoWriedProcess;
import com.lily.teacup.fragment_bridge.BridgeManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public abstract class BaseFragment  extends Fragment implements FragmentDataListener {



    private View mRootView;

    protected BridgeManager bridgeManager;

    public void setBridgeManager(BridgeManager bridgeManager) {
        this.bridgeManager = bridgeManager;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView=inflater.inflate(getLayoutResId(),container,false);
        FindKnifeProcess.bind(this,mRootView);
        AutoWriedProcess.bind(this);
        init(savedInstanceState);
        return mRootView;
    }

    public abstract int getLayoutResId();

    protected abstract void init(Bundle savedInstanceState);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof BaseActivity){
            BaseActivity baseActivity= (BaseActivity) context;

//            baseActivity.setFunctionForFragment(getFragmentTag(),getFragmentType());

            onBridge(baseActivity);
        }
    }

    /**
     *  获取当前Fragment的View对象
     * @return
     */
    protected View getCurrentView(){
        return mRootView;
    }


    /**
     * 具体实现activity和fragment的通讯
     * @param baseActivity
     */
    public void onBridge(BaseActivity baseActivity){ }


    /**
     * 页面无值跳转
     *
     * @param intent
     * @param className
     */
    protected void start(Intent intent, String className) {
        try {
            Class clz = Class.forName(className);
            intent.setClass(getContext(), clz);
            startActivity(intent);
        } catch (ClassNotFoundException e) {

        }
    }

}
