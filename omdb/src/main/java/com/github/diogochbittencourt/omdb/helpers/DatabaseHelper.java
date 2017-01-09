package com.github.diogochbittencourt.omdb.helpers;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.github.diogochbittencourt.omdb.models.Movie;

import java.util.List;

/**
 * Created by Diogo Bittencourt on 09/01/17.
 */

public final class DatabaseHelper {

    public DatabaseHelper() {
    }

    public static boolean isMovieSaved(String imdbId) {
        return new Select().from(Movie.class)
                .where("imdbid = ?", imdbId)
                .exists();
    }

    public static void saveMovie(Movie movie) {
        movie.save();
    }

    public static void deleteMovie(Movie movie) {
        new Delete().from(Movie.class).where("imdbid = ?", movie.getImdbid()).execute();
    }

    public static List<Movie> getSavedMovies() {
        return new Select()
                .from(Movie.class)
                .orderBy("Title ASC")
                .execute();
    }
}