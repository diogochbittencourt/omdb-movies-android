package com.github.diogochbittencourt.omdb.moviedetail;

import com.github.diogochbittencourt.omdb.BasePresenter;
import com.github.diogochbittencourt.omdb.helpers.DatabaseHelper;
import com.github.diogochbittencourt.omdb.models.Movie;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Diogo Bittencourt on 08/01/17.
 */

class MovieDetailPresenter extends BasePresenter implements MovieDetailContract.Presenter {

    private MovieDetailContract.View view;

    @Inject
    MovieDetailPresenter(MovieDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void onScreenCreated(String movieId) {
        if (DatabaseHelper.isMovieSaved(movieId)) {
            view.setFabIconDelete();
        } else {
            view.setFabIconSave();
        }
    }

    @Override
    public void onFabButtonClick(Movie movie) {
        if (DatabaseHelper.isMovieSaved(movie.getImdbid())) {
            deleteMovie(movie);
            view.setFabIconSave();
            view.showDeleteMovieSuccessMessage();
        } else {
            saveMovie(movie);
            view.setFabIconDelete();
            view.showSaveMovieSuccessMessage();
        }
    }

    private void deleteMovie(Movie movie) {
        Observable.create((Observable.OnSubscribe<Void>) subscriber -> DatabaseHelper.deleteMovie(movie))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    private void saveMovie(Movie movie) {
        Observable.create((Observable.OnSubscribe<Void>) subscriber -> DatabaseHelper.saveMovie(movie))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    @Override
    protected void destroy() {
    }
}