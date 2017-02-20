package com.fuzhucheng.rxjavartrofitrecyclerview.fragment.base;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by 符柱成 on 2016/12/2.
 */
public abstract class BaseFragment extends Fragment {
    private Handler handler = new Handler();
    private Runnable loadDataTask = new Runnable() {
        @Override
        public void run() {
            loadData();
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView();
        initEvent();
        // 延迟加载数据，减少卡顿
        handler.postDelayed(loadDataTask, 500);
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isVisibleToUser) {
            handler.removeCallbacks(loadDataTask);
        }
    }

    protected abstract void initView();

    protected abstract void initEvent();

    protected abstract void loadData();

    protected abstract int getLayoutId();


}
