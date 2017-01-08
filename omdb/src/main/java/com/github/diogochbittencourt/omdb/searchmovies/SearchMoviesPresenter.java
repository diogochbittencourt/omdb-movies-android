package com.github.diogochbittencourt.omdb.searchmovies;

import com.github.diogochbittencourt.omdb.BasePresenter;
import com.github.diogochbittencourt.omdb.models.Movie;
import com.github.diogochbittencourt.omdb.models.MovieSearchResult;
import com.github.diogochbittencourt.omdb.networking.OmdbRepository;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Diogo Bittencourt on 07/01/17.
 */

class SearchMoviesPresenter extends BasePresenter implements SearchMoviesContract.Presenter {

    private static final int QUERY_MIN_LENGTH = 3;
    private static final int DEBOUNCE_TIMEOUT = 300;

    private SearchMoviesContract.View view;
    private OmdbRepository omdbRepository;
    private Subscription textSubmissionSubscription;
    private Subscription textChangeSubscription;

    @Inject
    SearchMoviesPresenter(SearchMoviesContract.View view, OmdbRepository omdbRepository) {
        this.view = view;
        this.omdbRepository = omdbRepository;
    }

    @Override
    public void onQueryTextSubmit(String query) {
        if (query.length() > QUERY_MIN_LENGTH) {
            Observable<List<Movie>> observable = omdbRepository.searchMoviesByQuery(query);
            textSubmissionSubscription = observable.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io()).subscribe(new Subscriber<List<Movie>>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            showSearchMovieError();
                        }

                        @Override
                        public void onNext(List<Movie> movies) {
                        }
                    });
        } else {
            view.hideKeyboard();
            view.showQueryMinLengthError();
        }
    }

    @Override
    public void onQueryTextChange(String query) {
        if (query.length() > QUERY_MIN_LENGTH) {
            if (textChangeSubscription != null && !textChangeSubscription.isUnsubscribed()) {
                textChangeSubscription.unsubscribe();
                //TODO clear adapter
            }

            Observable<MovieSearchResult> observable = omdbRepository.searchMoviesByPartialQuery(query);
            textChangeSubscription = observable
                    .debounce(DEBOUNCE_TIMEOUT, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Subscriber<MovieSearchResult>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            showSearchMovieError();
                        }

                        @Override
                        public void onNext(MovieSearchResult movieSearchResult) {
                        }
                    });
        }
    }

    private void showSearchMovieError() {
        view.showSearchMovieError();
    }

    @Override
    protected void destroy() {
        if (textSubmissionSubscription != null && !textSubmissionSubscription.isUnsubscribed()) {
            textSubmissionSubscription.unsubscribe();
        }
        if (textChangeSubscription != null && !textChangeSubscription.isUnsubscribed()) {
            textChangeSubscription.unsubscribe();
        }
    }
}