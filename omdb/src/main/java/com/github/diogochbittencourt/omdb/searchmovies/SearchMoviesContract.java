package com.github.diogochbittencourt.omdb.searchmovies;

import com.github.diogochbittencourt.omdb.models.Movie;

import java.util.List;

/**
 * Created by Diogo Bittencourt on 07/01/17.
 */

public interface SearchMoviesContract {

    interface View {
        void showMovies(List<Movie> movies);

        void hideKeyboard();

        void showQueryMinLengthError();

        void showSearchMovieError();
    }

    interface Presenter {
        void onQueryTextSubmit(String query);

        void onQueryTextChange(String query);
    }
}