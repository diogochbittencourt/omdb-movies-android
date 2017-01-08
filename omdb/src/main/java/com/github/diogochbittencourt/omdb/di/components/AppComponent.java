package com.github.diogochbittencourt.omdb.di.components;

import com.github.diogochbittencourt.omdb.di.modules.AppModule;
import com.github.diogochbittencourt.omdb.di.modules.NetModule;
import com.github.diogochbittencourt.omdb.networking.OmdbAPI;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Diogo Bittencourt on 04/01/17.
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {
    OmdbAPI omdbApi();
}