package com.github.diogochbittencourt.omdb.movies;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.diogochbittencourt.omdb.R;
import com.github.diogochbittencourt.omdb.models.Movie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Diogo Bittencourt on 08/01/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private final Context context;
    private final List<Movie> movies;
    private final OnItemClickListener onItemClickListener;

    public MoviesAdapter(Context context, List<Movie> movies, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.movies = movies;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.click(movie, onItemClickListener);
        loadMoviePoster(holder, movie.getPoster());
        holder.movieTitle.setText(context.getString(R.string.movie_detail_title, movie.getTitle(), movie.getYear()));
        holder.movieActors.setText(!TextUtils.isEmpty(movie.getActors()) ? movie.getActors() : "");
        holder.movieCountry.setText(!TextUtils.isEmpty(movie.getCountry()) ? movie.getCountry() : "");
        holder.movieRuntime.setText(!TextUtils.isEmpty(movie.getRuntime()) ? movie.getRuntime() : "");
        holder.movieLanguage.setText(!TextUtils.isEmpty(movie.getLanguage()) ? movie.getLanguage() : "");
        holder.movieImdbRating.setText(!TextUtils.isEmpty(movie.getImdbrating()) ? movie.getImdbrating() : "");
    }

    private void loadMoviePoster(ViewHolder holder, String poster) {
        Glide.with(context)
                .load(poster)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                .placeholder(R.drawable.ic_film)
                .into(holder.moviePoster);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public interface OnItemClickListener {
        void onClick(Movie movie);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.movie_poster)
        AppCompatImageView moviePoster;
        @BindView(R.id.movie_title)
        AppCompatTextView movieTitle;
        @BindView(R.id.movie_actors)
        AppCompatTextView movieActors;
        @BindView(R.id.movie_country)
        AppCompatTextView movieCountry;
        @BindView(R.id.movie_runtime)
        AppCompatTextView movieRuntime;
        @BindView(R.id.movie_language)
        AppCompatTextView movieLanguage;
        @BindView(R.id.movie_imdb_rating)
        AppCompatTextView movieImdbRating;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void click(final Movie movie, final OnItemClickListener listener) {
            itemView.setOnClickListener(v -> listener.onClick(movie));
        }
    }
}