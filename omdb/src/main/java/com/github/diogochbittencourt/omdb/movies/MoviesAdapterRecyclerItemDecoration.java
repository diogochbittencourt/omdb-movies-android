package com.github.diogochbittencourt.omdb.movies;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.diogochbittencourt.omdb.R;

/**
 * Created by Diogo Bittencourt on 09/01/17.
 */
public final class MoviesAdapterRecyclerItemDecoration extends RecyclerView.ItemDecoration {

    private final Drawable divider;

    public MoviesAdapterRecyclerItemDecoration(final Context context) {
        divider = ContextCompat.getDrawable(context, R.drawable.recycler_view_divider);
    }

    @Override
    public void onDrawOver(final Canvas c, final RecyclerView parent,
                           final RecyclerView.State state) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);

            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + divider.getIntrinsicHeight();

            divider.setBounds(left, top, right, bottom);
            divider.draw(c);
        }
    }
}