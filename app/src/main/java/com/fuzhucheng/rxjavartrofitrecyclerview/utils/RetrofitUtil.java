package com.fuzhucheng.rxjavartrofitrecyclerview.utils;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.fuzhucheng.rxjavartrofitrecyclerview.App;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 符柱成 on 2017/1/8.
 */

public class RetrofitUtil {
    private static final String TAG = "retrofit";
    //TODO:修改主机地址
    private static final String BASE_URL = "https://api.douban.com";
    private static final int DEFAULT_TIMEOUT = 5;
    private static Retrofit retrofit;

    //实例化私有
    private RetrofitUtil(){

    }

    public static Retrofit getInstance(){
        if(retrofit==null) {
            ClearableCookieJar cookieJar =
                    new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(App.getInstance()));
            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();                //okhttp创建，写入缓存机制，还有addInterceptor
            httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            File cacheFile = new File(App.getInstance().getCacheDir(), "superman");
            Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
            httpClientBuilder.cache(cache);
            httpClientBuilder.cookieJar(cookieJar);
            httpClientBuilder.addInterceptor(LoggingInterceptor);
            httpClientBuilder.addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR);
            httpClientBuilder.addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR);

            return new Retrofit.Builder()                          //retrofit的创建。
                    .client(httpClientBuilder.build())          //传入okhttp
                    .addConverterFactory(GsonConverterFactory.create())             //传入gson解析手段
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())           //传入异步手段
                    .baseUrl(BASE_URL)              //传入服务器地址
                    .build();
        }else{
            return retrofit;
        }
    }
    private static final Interceptor LoggingInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            Logger.t(TAG).i(String.format("Sending request %s on %s%n%s", request.url(),  chain.connection(), request.headers()));
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            Logger.t(TAG).i(String.format("Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            return response;
        }
    };
    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if(!NetUtil.isConnected(App.getInstance())){
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                Logger.t(TAG).w("no network");
            }
            Response originalResponse = chain.proceed(request);
            if(NetUtil.isConnected(App.getInstance())){
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            }else{
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };
}
