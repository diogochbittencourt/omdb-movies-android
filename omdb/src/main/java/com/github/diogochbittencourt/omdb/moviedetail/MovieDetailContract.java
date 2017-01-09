package com.github.diogochbittencourt.omdb.moviedetail;

/**
 * Created by Diogo Bittencourt on 08/01/17.
 */

public interface MovieDetailContract {

    interface View {
    }

    interface Presenter {
        void onSaveMovieButtonClick();
    }
}