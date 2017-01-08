package com.github.diogochbittencourt.omdb;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Diogo Bittencourt on 04/01/17.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(final Context context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(context));
    }
}