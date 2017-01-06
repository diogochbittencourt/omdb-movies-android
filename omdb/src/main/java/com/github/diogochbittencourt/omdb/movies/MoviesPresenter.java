package com.github.diogochbittencourt.omdb.movies;

import javax.inject.Inject;

/**
 * Created by Diogo Bittencourt on 04/01/17.
 */

public class MoviesPresenter implements MoviesContract.Presenter {

    private MoviesContract.View view;

    @Inject
    public MoviesPresenter(MoviesContract.View view) {
        this.view = view;
    }

    @Override
    public void loadMovies() {
    }

    @Override
    public void addMovie() {
        view.openSearchMovieScreen();
    }

    @Override
    public void showMovieDetail() {

    }
}
