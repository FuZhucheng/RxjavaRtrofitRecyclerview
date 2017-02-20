package com.fuzhucheng.rxjavartrofitrecyclerview.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 符柱成 on 2016/12/4.
 */
public class HomeViewPagerAdapter extends FragmentPagerAdapter {

    private List<String> title;
    private List<Fragment> views;

    public HomeViewPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles ) {
        super(fm);
        this.title = titles;
        this.views = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return views.get(position);
    }

    @Override
    public int getCount() {
        return views.size();
    }


    //配置标题的方法
    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}