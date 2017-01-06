package com.github.diogochbittencourt.omdb.searchmovies;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.diogochbittencourt.omdb.BaseActivity;
import com.github.diogochbittencourt.omdb.R;

/**
 * Created by Diogo Bittencourt on 04/01/17.
 */

public class SearchMoviesActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movies);
    }
}
