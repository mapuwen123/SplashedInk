package com.marvin.splashedink.utils.glide;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.integration.okhttp3.OkHttpLibraryGlideModule;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.GlideModule;
import com.marvin.splashedink.common.BuildConfig;

import static com.bumptech.glide.load.engine.cache.DiskCache.Factory.DEFAULT_DISK_CACHE_SIZE;

/**
 * Created by mapuw on 2017/1/10.
 */

public class MyGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDiskCache(new DiskLruCacheFactory(BuildConfig.AppDir + "/IMGCache", DEFAULT_DISK_CACHE_SIZE));
    }

    @Override
    public void registerComponents(Context context, Registry registry) {
        new OkHttpLibraryGlideModule().registerComponents(context, registry);
    }
}
