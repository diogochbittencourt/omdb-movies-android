package com.github.diogochbittencourt.omdb.moviedetail;

import com.github.diogochbittencourt.omdb.di.scopes.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Diogo Bittencourt on 08/01/17.
 */
@Module
class MovieDetailModule {

    private final MovieDetailContract.View view;

    MovieDetailModule(MovieDetailContract.View view) {
        this.view = view;
    }

    @Provides
    @PerActivity
    MovieDetailContract.View providesMovieDetailContractView() {
        return view;
    }
}