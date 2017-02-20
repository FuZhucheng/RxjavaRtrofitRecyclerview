package com.fuzhucheng.rxjavartrofitrecyclerview.activity.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by 符柱成 on 2016/12/2.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static List<Activity> activities = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.i("Activity--->" + getClass().getSimpleName());
        addActivity(this);

        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        } else {
            throw new IllegalArgumentException("返回一个正确的ContentView");
        }
        ButterKnife.bind(this);

        initView();
        initEvent();
        loadData();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initEvent();

    protected abstract void loadData();

    @Override
    protected void onPause() {
        super.onPause();
        removeActivity(this);
    }


    // 添加Activity到容器中
    private void addActivity(Activity activity) {
        if (activity != null && !activities.contains(activity)) {
            activities.add(activity);
        }
    }

    private void removeActivity(Activity activity) {
        if (activity != null && activities.contains(activity)) {
            activities.remove(activity);
        }
    }

    // 退出整个APP
    public static void exit() {
        if (activities != null && activities.size() > 0) {
            for (Activity activity : activities) {
                activity.finish();
            }
        }
        System.exit(0);
    }
}
