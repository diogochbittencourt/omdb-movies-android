package com.github.diogochbittencourt.omdb.movies;

import com.github.diogochbittencourt.omdb.helpers.DatabaseHelper;
import com.github.diogochbittencourt.omdb.models.Movie;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Diogo Bittencourt on 04/01/17.
 */

class MoviesPresenter implements MoviesContract.Presenter {

    private MoviesContract.View view;

    @Inject
    MoviesPresenter(MoviesContract.View view) {
        this.view = view;
    }

    @Override
    public void loadSavedMovies() {
        loadMovies();
    }

    private void loadMovies() {
        Observable.just(DatabaseHelper.getSavedMovies()).subscribe(new Subscriber<List<Movie>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                view.showGetSavedMoviesError();
            }

            @Override
            public void onNext(List<Movie> movies) {
                if (movies != null && !movies.isEmpty()) {
                    view.showSavedMovies(movies);
                } else {
                    view.showEmptySavedMoviesMessage();
                }
            }
        });
    }

    @Override
    public void onAddMoviesButtonClick() {
        view.openSearchMovieScreen();
    }
}