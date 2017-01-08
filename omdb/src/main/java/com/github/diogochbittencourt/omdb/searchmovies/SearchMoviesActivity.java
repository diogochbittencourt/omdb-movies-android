package com.github.diogochbittencourt.omdb.searchmovies;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.github.diogochbittencourt.omdb.AppContext;
import com.github.diogochbittencourt.omdb.BaseActivity;
import com.github.diogochbittencourt.omdb.R;
import com.github.diogochbittencourt.omdb.models.Movie;
import com.github.diogochbittencourt.omdb.utils.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Diogo Bittencourt on 04/01/17.
 */

public class SearchMoviesActivity extends BaseActivity implements SearchMoviesContract.View,
        SearchView.OnQueryTextListener {

    @BindView(R.id.search_movies_parent_layout)
    ConstraintLayout parentLayout;

    @Inject
    SearchMoviesPresenter searchMoviesPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movies);
        ButterKnife.bind(this);
        configureInjectionComponents();
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
    public void showMovies(List<Movie> movies) {

    }

    @Override
    public void hideKeyboard() {
        Utils.hideSoftKeyboard(this);
    }

    @Override
    public void showQueryMinLengthError() {
        Snackbar.make(parentLayout, getString(R.string.search_movie_min_length), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showSearchMovieError() {
        Snackbar.make(parentLayout, getString(R.string.search_movie_error), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        searchMoviesPresenter.onQueryTextSubmit(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        searchMoviesPresenter.onQueryTextChange(newText);
        return true;
    }

    @Override
    protected void onDestroy() {
        searchMoviesPresenter.destroy();
        super.onDestroy();
    }
}