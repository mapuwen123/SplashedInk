package com.marvin.splashedink.base;

import android.content.Context;
import android.text.TextUtils;

import com.marvin.splashedink.common.APIConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mapuw on 2016/12/9.
 */

public abstract class BaseRetrofit extends RuntimeException {

    private static final OkHttpClient.Builder mHttpClientBuilder = new OkHttpClient.Builder();
    private static Retrofit mRetrofit;


    public static Retrofit getRetrofit(Context context) {
        if (mRetrofit == null) {

            //设定30秒超时,拦截http请求进行监控重写或重试,打印网络请求
            mHttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(interceptor);
            OkHttpClient mOkHttpClient = mHttpClientBuilder.build();


            //构建Retrofit
            mRetrofit = new Retrofit.Builder()//配置服务器路径
                    .baseUrl(APIConfig.HOST)
                    //配置转化库，默认是Gson
                    .addConverterFactory(GsonConverterFactory.create())
                    //配置回调库，采用RxJava
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    //设置OKHttpClient为网络客户端
                    .client(mOkHttpClient)
                    .build();
        }
        return mRetrofit;
    }


    static Interceptor interceptor = chain -> {
        Request request = chain.request();
        Response response = chain.proceed(request);
        String cacheControl = request.cacheControl().toString();
        if (TextUtils.isEmpty(cacheControl)) {
            cacheControl = "public, max-age=60";
        }
        return response.newBuilder()
                .header("Cache-Control", cacheControl)
                .removeHeader("Pragma")
                .build();
    };

}
