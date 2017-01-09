package com.github.diogochbittencourt.omdb.moviedetail;

import com.github.diogochbittencourt.omdb.BasePresenter;

import javax.inject.Inject;

/**
 * Created by Diogo Bittencourt on 08/01/17.
 */

public class MovieDetailPresenter extends BasePresenter implements MovieDetailContract.Presenter {

    private MovieDetailContract.View view;

    @Inject
    public MovieDetailPresenter(MovieDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void onSaveMovieButtonClick() {

    }

    @Override
    protected void destroy() {
    }
}