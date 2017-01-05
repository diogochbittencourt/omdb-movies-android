package com.github.diogochbittencourt.omdb;

import android.app.Application;

import com.github.diogochbittencourt.omdb.di.components.AppComponent;
import com.github.diogochbittencourt.omdb.di.components.DaggerAppComponent;
import com.github.diogochbittencourt.omdb.di.modules.AppModule;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Diogo Bittencourt on 04/01/17.
 */

public class OmdbApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        configureInjectionComponents();
        configureAppContext();
        configureCalligraphy();
    }

    private void configureCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath(
                getResources().getString(R.string.roboto_regular)).setFontAttrId(R.attr.fontPath).build());
    }

    private void configureInjectionComponents() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    private void configureAppContext() {
        AppContext.setAppComponent(appComponent);
    }
}