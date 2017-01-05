package com.github.diogochbittencourt.omdb;

import com.github.diogochbittencourt.omdb.di.components.AppComponent;

/**
 * Created by Diogo Bittencourt on 04/01/17.
 */

public class AppContext {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static void setAppComponent(AppComponent appComponent) {
        AppContext.appComponent = appComponent;
    }
}