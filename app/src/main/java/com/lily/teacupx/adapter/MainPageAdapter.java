package com.lily.teacupx.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.lily.teacup.basicclass.BaseFragment;

import java.util.List;

/**
 * @author Lily
 * @date 2019/7/6 0006.
 * GitHub：https://github.com/JulyLily
 * email：228821309@qq.com
 * description：
 */
public class MainPageAdapter extends FragmentPagerAdapter {
    private final BaseFragment[] list;
    private final String[] strings;

    public MainPageAdapter(FragmentManager fm, BaseFragment[] fragmentList,String[] strings) {
        super(fm);
        this.list=fragmentList;
        this.strings=strings;
    }

    @Override
    public Fragment getItem(int position) {
        return list[position];
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return strings[position];
    }
}
