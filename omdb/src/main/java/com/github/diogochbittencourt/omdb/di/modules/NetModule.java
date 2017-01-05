package com.github.diogochbittencourt.omdb.di.modules;

import com.github.diogochbittencourt.omdb.networking.NetAdapter;
import com.github.diogochbittencourt.omdb.networking.OmdbAPI;

import dagger.Module;
import dagger.Provides;


/**
 * Created by Diogo Bittencourt on 04/01/17.
 */
@Module
public class NetModule {

    @Provides
    public OmdbAPI providesOmdbAPI() {
        return NetAdapter.getApi(OmdbAPI.class);
    }
}