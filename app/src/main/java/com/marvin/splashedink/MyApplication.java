package com.marvin.splashedink;

import android.app.Application;
import android.content.Context;

import com.marvin.splashedink.base.BaseRetrofit;
import com.marvin.splashedink.common.BuildConfig;
import com.marvin.splashedink.network.NetWorkService;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.umeng.socialize.PlatformConfig;

import io.realm.Realm;
import io.realm.RealmConfiguration;
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
        // Realm初始化
        Realm.init(this);
        RealmConfiguration config = new  RealmConfiguration.Builder()
                .name("SplashedInk.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("1106288406", "bm7EXPXnKdd6mcOY");
        PlatformConfig.setSinaWeibo("3854770541", "9010e556f5e273fb95f5c3e2a95817fa", "http://sns.whalecloud.com");
        PlatformConfig.setAlipay("2017071407749552");
    }

    public static Context getContext() {
        return context;
    }
}
