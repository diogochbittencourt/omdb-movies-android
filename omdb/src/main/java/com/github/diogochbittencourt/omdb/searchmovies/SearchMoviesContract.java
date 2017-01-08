package com.github.diogochbittencourt.omdb.searchmovies;

import com.github.diogochbittencourt.omdb.models.Movie;

import java.util.List;

/**
 * Created by Diogo Bittencourt on 07/01/17.
 */

public interface SearchMoviesContract {

    interface View {
        void onSearchCompleted();

        void onQueryTextSubmitted(List<Movie> movies);

        void onQueryTextChanged(List<Movie> movies);

        void hideKeyboard();

        void clearMoviesList();

        void showQueryMinLengthError();

        void showSearchMovieError();
    }

    interface Presenter {
        void onQueryTextSubmit(String query);

        void onQueryTextChange(String query);
    }
}