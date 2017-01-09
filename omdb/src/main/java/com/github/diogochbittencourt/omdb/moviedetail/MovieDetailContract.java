package com.github.diogochbittencourt.omdb.moviedetail;

import com.github.diogochbittencourt.omdb.models.Movie;

/**
 * Created by Diogo Bittencourt on 08/01/17.
 */

interface MovieDetailContract {

    interface View {
        void setFabIconSave();

        void setFabIconDelete();

        void showSaveMovieSuccessMessage();

        void showDeleteMovieSuccessMessage();
    }

    interface Presenter {
        void onScreenCreated(String movieId);

        void onFabButtonClick(Movie movie);
    }
}