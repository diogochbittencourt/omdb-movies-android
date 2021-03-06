package com.github.diogochbittencourt.omdb.movies;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.diogochbittencourt.omdb.AppContext;
import com.github.diogochbittencourt.omdb.BaseActivity;
import com.github.diogochbittencourt.omdb.R;
import com.github.diogochbittencourt.omdb.models.Movie;
import com.github.diogochbittencourt.omdb.searchmovies.SearchMoviesActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoviesActivity extends BaseActivity implements MoviesContract.View {

    private MoviesAdapter moviesAdapter;
    private List<Movie> movies;

    @Inject
    MoviesPresenter moviesPresenter;

    @BindView(R.id.movies_list)
    RecyclerView moviesList;
    @BindView(R.id.movies_fab)
    FloatingActionButton fabAddMovie;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.parent_layout)
    ConstraintLayout parentLayout;
    @BindView(R.id.movies_empty_list_text)
    AppCompatTextView emptyListText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);
        configureInjectionComponents();
        movies = new ArrayList<>();
        configureViews();
    }

    private void configureViews() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.movies_screen_title));
        }

        moviesAdapter = new MoviesAdapter(this, movies, this::openMovieDetail);
        moviesList.setLayoutManager(new LinearLayoutManager(this));
        moviesList.addItemDecoration(new MoviesAdapterRecyclerItemDecoration(this));
        moviesList.setAdapter(moviesAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        moviesPresenter.loadSavedMovies();
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
    public void openSearchMovieScreen() {
        Intent intent = new Intent(this, SearchMoviesActivity.class);
        intent.setAction(Intent.ACTION_SEARCH);
        startActivity(intent);
    }

    @Override
    public void showEmptySavedMoviesMessage() {
        movies.clear();
        moviesAdapter.notifyDataSetChanged();
        emptyListText.setVisibility(View.VISIBLE);
        moviesList.setVisibility(View.GONE);
    }

    @Override
    public void showGetSavedMoviesError() {
        showSnackBarMessage(parentLayout, getString(R.string.movies_get_saved_movies_error));
    }

    @Override
    public void showSavedMovies(List<Movie> moviesResult) {
        if (moviesResult != null && !moviesResult.isEmpty()) {
            movies.clear();
            movies.addAll(moviesResult);
            moviesAdapter.notifyDataSetChanged();
            moviesList.setVisibility(View.VISIBLE);
            emptyListText.setVisibility(View.GONE);
        } else {
            moviesList.setVisibility(View.GONE);
            emptyListText.setVisibility(View.VISIBLE);
        }
    }
}