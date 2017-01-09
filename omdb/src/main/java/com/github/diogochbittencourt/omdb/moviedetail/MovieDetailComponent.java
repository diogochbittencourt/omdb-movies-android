package com.github.diogochbittencourt.omdb.moviedetail;

import com.github.diogochbittencourt.omdb.di.components.AppComponent;
import com.github.diogochbittencourt.omdb.di.scopes.PerActivity;

import dagger.Component;

/**
 * Created by Diogo Bittencourt on 08/01/17.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = MovieDetailModule.class)
interface MovieDetailComponent {
    void inject(MovieDetailActivity movieDetailActivity);
}
