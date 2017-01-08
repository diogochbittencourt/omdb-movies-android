package com.github.diogochbittencourt.omdb.models;

import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Diogo Bittencourt on 07/01/17.
 */

public class MovieSearchResult implements Parcelable {

    @SerializedName("Search")
    private List<MovieSearch> search;

    public List<MovieSearch> getSearch() {
        return search;
    }

    public void setSearch(List<MovieSearch> search) {
        this.search = search;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.search);
    }

    public MovieSearchResult() {
    }

    protected MovieSearchResult(Parcel in) {
        this.search = in.createTypedArrayList(MovieSearch.CREATOR);
    }

    public static final Creator<MovieSearchResult> CREATOR = new Creator<MovieSearchResult>() {
        @Override
        public MovieSearchResult createFromParcel(Parcel source) {
            return new MovieSearchResult(source);
        }

        @Override
        public MovieSearchResult[] newArray(int size) {
            return new MovieSearchResult[size];
        }
    };
}