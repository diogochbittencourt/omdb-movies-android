package com.github.diogochbittencourt.omdb.movies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;

import com.github.diogochbittencourt.omdb.AppContext;
import com.github.diogochbittencourt.omdb.BaseActivity;
import com.github.diogochbittencourt.omdb.R;
import com.github.diogochbittencourt.omdb.models.Movie;
import com.github.diogochbittencourt.omdb.searchmovies.SearchMoviesActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoviesActivity extends BaseActivity implements MoviesContract.View {

    @BindView(R.id.movies_list)
    RecyclerView moviesList;
    @BindView(R.id.movies_fab)
    FloatingActionButton moviesFab;

    @Inject
    MoviesPresenter moviesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);
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
        moviesPresenter.addMovie();
    }

    @Override
    public void showMovies(List<Movie> movies) {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void openSearchMovieScreen() {
        startActivity(new Intent(this, SearchMoviesActivity.class));
    }
}