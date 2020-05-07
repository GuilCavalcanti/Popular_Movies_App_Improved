package com.guil.popularmoviesapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_movies")
public class FavoriteMovies implements Parcelable {

    public int getMainID() {
        return MainID;
    }

    public void setMainID(int mainID) {
        MainID = mainID;
    }

    @PrimaryKey(autoGenerate = true)
    private int MainID;
    @NonNull
    @ColumnInfo(name = "poster_path")
    private String poster_path;
    @ColumnInfo(name = "id")
    private String id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "overview")
    private String overview;
    @ColumnInfo(name = "release_date")
    private String release_date;
    @ColumnInfo(name = "vote_average")
    private String vote_average;
    @ColumnInfo(name = "inMovieDB")
    private String inMovieDB;

    public FavoriteMovies(@NonNull String poster_path, String id, String title, String overview, String release_date, String vote_average, String inMovieDB) {
        this.poster_path = poster_path;
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.release_date = release_date;
        this.vote_average = vote_average;
        this.inMovieDB = inMovieDB;
    }

    protected FavoriteMovies(Parcel in) {
        MainID = in.readInt();
        poster_path = in.readString();
        id = in.readString();
        title = in.readString();
        overview = in.readString();
        release_date = in.readString();
        vote_average = in.readString();
        inMovieDB = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(MainID);
        dest.writeString(poster_path);
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(overview);
        dest.writeString(release_date);
        dest.writeString(vote_average);
        dest.writeString(inMovieDB);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FavoriteMovies> CREATOR = new Creator<FavoriteMovies>() {
        @Override
        public FavoriteMovies createFromParcel(Parcel in) {
            return new FavoriteMovies(in);
        }

        @Override
        public FavoriteMovies[] newArray(int size) {
            return new FavoriteMovies[size];
        }
    };

    @NonNull
    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(@NonNull String poster_path) {
        this.poster_path = poster_path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getInMovieDB() {
        return inMovieDB;
    }

    public void setInMovieDB(String inMovieDB) {
        this.inMovieDB = inMovieDB;
    }

    @Override
    public String toString() {
        return "FavoriteMovies{" +
                "MainID=" + MainID +
                ", poster_path='" + poster_path + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", release_date='" + release_date + '\'' +
                ", vote_average='" + vote_average + '\'' +
                ", inMovieDB='" + inMovieDB + '\'' +
                '}';
    }
}
