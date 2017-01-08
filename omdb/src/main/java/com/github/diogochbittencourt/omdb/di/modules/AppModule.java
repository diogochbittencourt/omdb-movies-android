package com.github.diogochbittencourt.omdb.di.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.github.diogochbittencourt.omdb.OmdbApplication;
import com.github.diogochbittencourt.omdb.networking.NetAdapter;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Diogo Bittencourt on 04/01/17.
 */
@Module
public class AppModule {

    private final OmdbApplication application;

    public AppModule(OmdbApplication application) {
        this.application = application;
        NetAdapter.init(new File(application.getCacheDir(), "http"));
    }

    @Provides
    @Singleton
    public Application providesApplication() {
        return application;
    }

    @Provides
    @Singleton
    public SharedPreferences providesSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }
}