package com.github.diogochbittencourt.omdb.models;

import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Diogo Bittencourt on 04/01/17.
 */
@Table(name = "MyMovies")
public class Movie extends Model implements Parcelable {

    @SerializedName("imdbID")
    @Column(index = true, name = "imdbid", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    private String imdbid;

    @SerializedName("Title")
    @Column(name = "title")
    private String title;

    @SerializedName("Year")
    @Column(name = "year")
    private String year;

    @SerializedName("Rated")
    @Column(name = "rated")
    private String rated;

    @SerializedName("Released")
    @Column(name = "released")
    private String released;

    @SerializedName("Runtime")
    @Column(name = "runtime")
    private String runtime;

    @SerializedName("Genre")
    @Column(name = "genre")
    private String genre;

    @SerializedName("Director")
    @Column(name = "director")
    private String director;

    @SerializedName("Writer")
    @Column(name = "writer")
    private String writer;

    @SerializedName("Actors")
    @Column(name = "actors")
    private String actors;

    @SerializedName("Plot")
    @Column(name = "plot")
    private String plot;

    @SerializedName("Language")
    @Column(name = "language")
    private String language;

    @SerializedName("Country")
    @Column(name = "country")
    private String country;

    @SerializedName("Awards")
    private String awards;

    @SerializedName("Poster")
    @Column(name = "poster")
    private String poster;

    @SerializedName("Metascore")
    @Column(name = "metascore")
    private String metascore;

    @SerializedName("imdbRating")
    @Column(name = "imdbrating")
    private String imdbrating;

    @SerializedName("imdbVotes")
    @Column(name = "imdbvotes")
    private String imdbvotes;

    @SerializedName("Type")
    @Column(name = "type")
    private String type;

    @SerializedName("Response")
    @Column(name = "response")
    private String response;

    public Movie() {
    }

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

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public String getImdbrating() {
        return imdbrating;
    }

    public void setImdbrating(String imdbrating) {
        this.imdbrating = imdbrating;
    }

    public String getImdbvotes() {
        return imdbvotes;
    }

    public void setImdbvotes(String imdbvotes) {
        this.imdbvotes = imdbvotes;
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

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.year);
        dest.writeString(this.rated);
        dest.writeString(this.released);
        dest.writeString(this.runtime);
        dest.writeString(this.genre);
        dest.writeString(this.director);
        dest.writeString(this.writer);
        dest.writeString(this.actors);
        dest.writeString(this.plot);
        dest.writeString(this.language);
        dest.writeString(this.country);
        dest.writeString(this.awards);
        dest.writeString(this.poster);
        dest.writeString(this.metascore);
        dest.writeString(this.imdbrating);
        dest.writeString(this.imdbvotes);
        dest.writeString(this.imdbid);
        dest.writeString(this.type);
        dest.writeString(this.response);
    }

    protected Movie(Parcel in) {
        this.title = in.readString();
        this.year = in.readString();
        this.rated = in.readString();
        this.released = in.readString();
        this.runtime = in.readString();
        this.genre = in.readString();
        this.director = in.readString();
        this.writer = in.readString();
        this.actors = in.readString();
        this.plot = in.readString();
        this.language = in.readString();
        this.country = in.readString();
        this.awards = in.readString();
        this.poster = in.readString();
        this.metascore = in.readString();
        this.imdbrating = in.readString();
        this.imdbvotes = in.readString();
        this.imdbid = in.readString();
        this.type = in.readString();
        this.response = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}