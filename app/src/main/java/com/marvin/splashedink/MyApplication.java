package com.marvin.splashedink;

import android.app.Application;
import android.content.Context;

import com.marvin.splashedink.base.BaseRetrofit;
import com.marvin.splashedink.common.BuildConfig;
import com.marvin.splashedink.network.NetWorkService;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/7/11.
 */

public class MyApplication extends Application {
    private static Context context;

    public static Retrofit retrofit;
    public static NetWorkService retrofitService;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        retrofit = BaseRetrofit.getRetrofit(this);
        retrofitService = retrofit.create(NetWorkService.class);

        // Logger初始化
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.isDebug;
            }
        });
    }

    public static Context getContext() {
        return context;
    }
}
