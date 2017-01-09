package com.github.diogochbittencourt.omdb.searchmovies;

import com.github.diogochbittencourt.omdb.di.scopes.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Diogo Bittencourt on 07/01/17.
 */
@Module
class SearchMoviesModule {

    private final SearchMoviesContract.View view;

    SearchMoviesModule(SearchMoviesContract.View view) {
        this.view = view;
    }

    @Provides
    @PerActivity
    SearchMoviesContract.View providesSearchMoviesContractView() {
        return view;
    }
}