package com.github.diogochbittencourt.omdb.movies;

import com.github.diogochbittencourt.omdb.models.Movie;

import java.util.List;

/**
 * Created by Diogo Bittencourt on 04/01/17.
 */

public interface MoviesContract {

    interface View {
        void showMovies(List<Movie> movies);

        void openSearchMovieScreen();

        void openMovieDetailScreen();

        void showError(String message);
    }

    interface Presenter {
        void loadMovies();

        void onAddMoviesButtonClick();

        void onMovieClick();
    }
}