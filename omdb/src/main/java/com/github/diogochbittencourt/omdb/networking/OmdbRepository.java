package com.github.diogochbittencourt.omdb.networking;

import com.github.diogochbittencourt.omdb.models.Movie;
import com.github.diogochbittencourt.omdb.models.MovieSearchResult;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by Diogo Bittencourt on 07/01/17.
 */

public class OmdbRepository {

    private OmdbAPI omdbAPI;

    @Inject
    public OmdbRepository(OmdbAPI omdbAPI) {
        this.omdbAPI = omdbAPI;
    }

    public Observable<List<Movie>> searchMoviesByQuery(String query) {
        return omdbAPI.searchMoviesbyQuery(query, "short", "movie", "json").subscribeOn(Schedulers.newThread())
                .flatMap(searchResults -> Observable.from(searchResults.getSearch() != null
                        ? searchResults.getSearch() : Collections.emptyList()))
                .flatMap(search -> searchMoviesByTitle(search.getTitle())).toList();
    }

    private Observable<Movie> searchMoviesByTitle(String title) {
        return omdbAPI.searchMoviesByTitle(title, "short", "movie", "json").subscribeOn(Schedulers.newThread());
    }

    public Observable<MovieSearchResult> searchMoviesByPartialQuery(String query) {
        return omdbAPI.searchMoviesbyQuery(query, "short", "movie", "json");
    }
}