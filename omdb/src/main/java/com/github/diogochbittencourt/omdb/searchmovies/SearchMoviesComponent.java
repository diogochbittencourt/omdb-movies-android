package com.github.diogochbittencourt.omdb.searchmovies;

import com.github.diogochbittencourt.omdb.di.components.AppComponent;
import com.github.diogochbittencourt.omdb.di.scopes.PerActivity;

import dagger.Component;

/**
 * Created by Diogo Bittencourt on 07/01/17.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = SearchMoviesModule.class)
public interface SearchMoviesComponent {
    void inject(SearchMoviesActivity searchMoviesActivity);
}