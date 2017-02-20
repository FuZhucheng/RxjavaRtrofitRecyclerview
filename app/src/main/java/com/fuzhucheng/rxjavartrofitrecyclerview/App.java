package com.fuzhucheng.rxjavartrofitrecyclerview;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by 符柱成 on 2016/12/2.
 */
public class App extends Application {

    private static App app;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);

        app = this;
    }

    public static App getInstance() {
        return app;
    }
}
