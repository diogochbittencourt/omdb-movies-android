package com.github.diogochbittencourt.omdb;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.github.diogochbittencourt.omdb.models.Movie;
import com.github.diogochbittencourt.omdb.moviedetail.MovieDetailActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Diogo Bittencourt on 04/01/17.
 */

public class BaseActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    @Nullable
    Toolbar toolbar;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        initToolbar();
    }

    private void initToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    @Override
    protected void attachBaseContext(final Context context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(context));
    }

    protected void showSnackBarMessage(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    protected void hideSoftKeyboard() {
        View view = this.getCurrentFocus();

        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected void openMovieDetail(Movie movie) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra(MovieDetailActivity.MOVIE_EXTRA, movie);
        startActivity(intent);
    }
}