package com.github.diogochbittencourt.omdb.networking;

import com.github.diogochbittencourt.omdb.models.Movie;
import com.github.diogochbittencourt.omdb.models.MovieSearchResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Diogo Bittencourt on 04/01/17.
 */

public interface OmdbAPI {

    @GET("/")
    Observable<MovieSearchResult> searchMoviesbyQuery(@Query("s") String query,
                                                      @Query("plot") String plot,
                                                      @Query("type") String type,
                                                      @Query("r") String format);

    @GET("/")
    Observable<Movie> searchMoviesByTitle(@Query("t") String title,
                                          @Query("plot") String plot,
                                          @Query("type") String type,
                                          @Query("r") String format);
}