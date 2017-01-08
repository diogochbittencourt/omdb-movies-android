package com.github.diogochbittencourt.omdb.utils;

import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Diogo Bittencourt on 07/01/17.
 */

public final class Utils {

    public Utils() {
    }

    public static void hideSoftKeyboard(AppCompatActivity activity) {
        if (activity != null && activity.getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }
}