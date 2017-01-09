package com.github.diogochbittencourt.omdb.networking;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.github.diogochbittencourt.omdb.BuildConfig;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Diogo Bittencourt on 04/01/17.
 */

public class NetAdapter {

    private static final int CACHE_SIZE = 10 * 1024 * 1024;
    private static final int TIME_OUT = 45;
    private static final String BASE_URL = "http://www.omdbapi.com";

    private static Retrofit retrofit;

    private NetAdapter() {
    }

    public static void init(@Nullable File cacheDir) {
        createAdapter(cacheDir);
    }

    public static synchronized <T> T getApi(@NonNull Class<T> service) {
        return retrofit.create(service);
    }

    private static void createAdapter(@Nullable File cacheDir) {
        retrofit = new Retrofit.Builder()
                .client(createClient(cacheDir))
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    private static OkHttpClient createClient(File cacheDir) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (cacheDir != null) {
            Cache cache = new Cache(cacheDir, CACHE_SIZE);
            builder.cache(cache);
            builder.addInterceptor(new CachingInterceptor());
        }

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        builder.addInterceptor(logging);
        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);

        return builder.build();
    }
}