package com.lily.teacup.basicclass;


public interface FragmentDataListener {
    <T extends BaseBean> void onDataChangeListener(T t);
}
