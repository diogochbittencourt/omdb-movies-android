package com.github.diogochbittencourt.omdb.models;

import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Diogo Bittencourt on 07/01/17.
 */

public class MovieSearch implements Parcelable {

    @SerializedName("Title")
    private String title;
    @SerializedName("Year")
    private String year;
    @SerializedName("imdbID")
    private String imdbid;
    @SerializedName("Type")
    private String type;
    @SerializedName("Poster")
    private String poster;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbid() {
        return imdbid;
    }

    public void setImdbid(String imdbid) {
        this.imdbid = imdbid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.year);
        dest.writeString(this.imdbid);
        dest.writeString(this.type);
        dest.writeString(this.poster);
    }

    public MovieSearch() {
    }

    protected MovieSearch(Parcel in) {
        this.title = in.readString();
        this.year = in.readString();
        this.imdbid = in.readString();
        this.type = in.readString();
        this.poster = in.readString();
    }

    public static final Creator<MovieSearch> CREATOR = new Creator<MovieSearch>() {
        @Override
        public MovieSearch createFromParcel(Parcel source) {
            return new MovieSearch(source);
        }

        @Override
        public MovieSearch[] newArray(int size) {
            return new MovieSearch[size];
        }
    };
}