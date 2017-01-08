package com.github.diogochbittencourt.omdb.movies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.github.diogochbittencourt.omdb.AppContext;
import com.github.diogochbittencourt.omdb.BaseActivity;
import com.github.diogochbittencourt.omdb.R;
import com.github.diogochbittencourt.omdb.models.Movie;
import com.github.diogochbittencourt.omdb.searchmovies.SearchMoviesActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MoviesActivity extends BaseActivity implements MoviesContract.View {

    @Inject
    MoviesPresenter moviesPresenter;

    @BindView(R.id.movies_list)
    RecyclerView moviesList;
    @BindView(R.id.movies_fab)
    FloatingActionButton fabAddMovie;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        configureInjectionComponents();
    }

    private void configureInjectionComponents() {
        DaggerMoviesComponent.builder()
                .appComponent(AppContext.getAppComponent())
                .moviesModule(new MoviesModule(this))
                .build().inject(this);
    }

    @OnClick(R.id.movies_fab)
    public void onClick() {
        moviesPresenter.onAddMoviesButtonClick();
    }

    @Override
    public void showMovies(List<Movie> movies) {

    }

    @Override
    public void openSearchMovieScreen() {
        Intent intent = new Intent(this, SearchMoviesActivity.class);
        intent.setAction(Intent.ACTION_SEARCH);
        startActivity(intent);
    }

    @Override
    public void openMovieDetailScreen() {

    }

    @Override
    public void showError(String message) {

    }
}