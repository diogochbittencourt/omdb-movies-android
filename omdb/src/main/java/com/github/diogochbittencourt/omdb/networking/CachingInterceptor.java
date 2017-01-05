package com.github.diogochbittencourt.omdb.networking;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * Created by Diogo Bittencourt on 04/01/17.
 */

class CachingInterceptor implements Interceptor {

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        request = new Request.Builder()
                .cacheControl(new CacheControl.Builder()
                        .maxAge(1, TimeUnit.DAYS)
                        .minFresh(2, TimeUnit.HOURS)
                        .maxStale(4, TimeUnit.HOURS)
                        .build())
                .url(request.url())
                .build();

        return chain.proceed(request);
    }
}