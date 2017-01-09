package com.github.diogochbittencourt.omdb.movies;

import com.github.diogochbittencourt.omdb.models.Movie;

import java.util.List;

/**
 * Created by Diogo Bittencourt on 04/01/17.
 */

public interface MoviesContract {

    interface View {
        void openSearchMovieScreen();

        void showEmptySavedMoviesMessage();

        void showGetSavedMoviesError();

        void showSavedMovies(List<Movie> movies);
    }

    interface Presenter {
        void loadSavedMovies();

        void onAddMoviesButtonClick();
    }
}