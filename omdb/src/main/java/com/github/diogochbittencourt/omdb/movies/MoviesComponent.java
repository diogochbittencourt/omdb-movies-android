package com.github.diogochbittencourt.omdb.movies;

import com.github.diogochbittencourt.omdb.di.components.AppComponent;
import com.github.diogochbittencourt.omdb.di.scopes.PerActivity;

import dagger.Component;

/**
 * Created by Diogo Bittencourt on 04/01/17.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = MoviesModule.class)
public interface MoviesComponent {
    void inject(MoviesActivity activity);
}