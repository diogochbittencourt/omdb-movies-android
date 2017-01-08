package com.github.diogochbittencourt.omdb.searchmovies;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.diogochbittencourt.omdb.AppContext;
import com.github.diogochbittencourt.omdb.BaseActivity;
import com.github.diogochbittencourt.omdb.R;
import com.github.diogochbittencourt.omdb.models.Movie;
import com.github.diogochbittencourt.omdb.movies.MoviesAdapter;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Diogo Bittencourt on 04/01/17.
 */

public class SearchMoviesActivity extends BaseActivity implements SearchMoviesContract.View,
        SearchView.OnQueryTextListener {

    private MoviesAdapter moviesAdapter;
    private List<Movie> movies;

    @Inject
    SearchMoviesPresenter searchMoviesPresenter;

    @BindView(R.id.search_movies_parent_layout)
    ConstraintLayout parentLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.movies_list)
    RecyclerView moviesList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movies);
        configureInjectionComponents();
        movies = new ArrayList<>();
        configureViews();
    }

    private void configureViews() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        moviesAdapter = new MoviesAdapter(this, movies, movie -> openMovieDetail());
        moviesList.setLayoutManager(new LinearLayoutManager(this));
        moviesList.setAdapter(moviesAdapter);
    }

    private void openMovieDetail() {
        Toast.makeText(this, "Open Movie Detail", Toast.LENGTH_SHORT).show();
    }

    private void configureInjectionComponents() {
        DaggerSearchMoviesComponent.builder()
                .appComponent(AppContext.getAppComponent())
                .searchMoviesModule(new SearchMoviesModule(this))
                .build().inject(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.seach_movies_options_menu, menu);
        MenuItem search = menu.findItem(R.id.action_movie_search);
        SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = null;

        if (search != null) {
            searchView = (SearchView) search.getActionView();
        }

        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
            searchView.setOnQueryTextListener(this);
            searchView.setOnCloseListener(() -> {
                onCloseSearchClick();
                return false;
            });
        }

        return super.onCreateOptionsMenu(menu);
    }

    private void onCloseSearchClick() {
        //TODO
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        searchMoviesPresenter.onQueryTextSubmit(encodeQuery(query));
        return true;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        searchMoviesPresenter.onQueryTextChange(encodeQuery(query));
        return true;
    }

    private String encodeQuery(String query) {
        try {
            return URLEncoder.encode(query, "UTF-8");
        } catch (Exception ex) {
            return query;
        }
    }

    @Override
    public void onSearchCompleted() {
        moviesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onQueryTextSubmitted(List<Movie> moviesResult) {
        if (moviesResult != null && !moviesResult.isEmpty()) {
            movies.addAll(moviesResult);
        } else {
            hideKeyboard();
            showSnackBarMessage(parentLayout, getString(R.string.search_movie_not_found));
        }
    }

    @Override
    public void onQueryTextChanged(List<Movie> moviesResult) {
        if (moviesResult != null && !moviesResult.isEmpty()) {
            movies.addAll(moviesResult);
        }
    }

    @Override
    public void hideKeyboard() {
        hideSoftKeyboard();
    }

    @Override
    public void clearMoviesList() {
        if (!movies.isEmpty()) {
            movies.clear();
            moviesAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showQueryMinLengthError() {
        showSnackBarMessage(parentLayout, getString(R.string.search_movie_min_length));
    }

    @Override
    public void showSearchMovieError() {
        showSnackBarMessage(parentLayout, getString(R.string.search_movie_error));
    }

    @Override
    protected void onDestroy() {
        searchMoviesPresenter.destroy();
        super.onDestroy();
    }
}