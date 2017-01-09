package com.github.diogochbittencourt.omdb.moviedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.diogochbittencourt.omdb.AppContext;
import com.github.diogochbittencourt.omdb.BaseActivity;
import com.github.diogochbittencourt.omdb.R;
import com.github.diogochbittencourt.omdb.models.Movie;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Diogo Bittencourt on 04/01/17.
 */

public class MovieDetailActivity extends BaseActivity implements MovieDetailContract.View {

    public static final String MOVIE_EXTRA = "movie_extra";

    private Movie movie;

    @Inject
    MovieDetailPresenter movieDetailPresenter;

    @BindView(R.id.movie_detail_poster)
    AppCompatImageView moviePoster;
    @BindView(R.id.movie_genre)
    AppCompatTextView movieGenre;
    @BindView(R.id.movie_plot)
    AppCompatTextView moviePlot;
    @BindView(R.id.movie_actors)
    AppCompatTextView movieActors;
    @BindView(R.id.movie_director)
    AppCompatTextView movieDirector;
    @BindView(R.id.movie_country)
    AppCompatTextView movieCountry;
    @BindView(R.id.movie_runtime)
    AppCompatTextView movieRuntime;
    @BindView(R.id.movie_language)
    AppCompatTextView movieLanguage;
    @BindView(R.id.movie_imdb_rating)
    AppCompatTextView movieImdbRating;
    @BindView(R.id.movie_awards)
    AppCompatTextView movieAwards;
    @BindView(R.id.movie_detail_fab)
    FloatingActionButton movieDetailFab;
    @BindView(R.id.movie_detail_parent_layout)
    CoordinatorLayout parentLayout;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        configureInjectionComponents();
        movie = getIntent().getExtras().getParcelable(MOVIE_EXTRA);
        configureViews();
    }

    private void configureInjectionComponents() {
        DaggerMovieDetailComponent.builder()
                .appComponent(AppContext.getAppComponent())
                .movieDetailModule(new MovieDetailModule(this))
                .build().inject(this);
    }

    private void configureViews() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.movie_detail_title, movie.getTitle(), movie.getYear()));
            collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        }

        downloadMoviePoster();
        movieGenre.setText(getString(R.string.movie_detail_genre, movie.getGenre()));
        moviePlot.setText(movie.getPlot());
        movieActors.setText(getString(R.string.movie_detail_actors, movie.getActors()));
        movieDirector.setText(getString(R.string.movie_detail_director, movie.getDirector()));
        movieCountry.setText(getString(R.string.movie_detail_country, movie.getCountry()));
        movieRuntime.setText(getString(R.string.movie_detail_runtime, movie.getRuntime()));
        movieLanguage.setText(getString(R.string.movie_detail_language, movie.getLanguage()));
        movieImdbRating.setText(movie.getImdbrating());
        movieAwards.setText(getString(R.string.movie_detail_awards, movie.getAwards()));
    }

    private void downloadMoviePoster() {
        Glide.with(this)
                .load(movie.getPoster())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                .placeholder(R.drawable.ic_film)
                .into(moviePoster);
    }

    @OnClick(R.id.movie_detail_fab)
    public void onClick() {
        movieDetailPresenter.onSaveMovieButtonClick();
    }
}