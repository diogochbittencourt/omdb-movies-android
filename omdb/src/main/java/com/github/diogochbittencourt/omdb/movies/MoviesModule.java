package com.github.diogochbittencourt.omdb.movies;

import com.github.diogochbittencourt.omdb.di.scopes.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Diogo Bittencourt on 04/01/17.
 */
@Module
public class MoviesModule {

    private final MoviesContract.View view;

    public MoviesModule(MoviesContract.View view) {
        this.view = view;
    }

    @Provides
    @PerActivity
    MoviesContract.View providesMoviesContractView() {
        return view;
    }
}
