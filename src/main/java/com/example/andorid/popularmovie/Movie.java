package com.example.andorid.popularmovie;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private final String Title;
    private final String Date_Released;
    private final String RateMovie;
    private final String Poster_Path;
    private final String Description;

    Movie (String Title, String Date_Released, String RateMovie, String Poster_Path, String Description) {
        this.Title = Title;
        this.Date_Released = Date_Released;
        this.RateMovie = RateMovie;
        this.Poster_Path = Poster_Path;
        this.Description = Description;
    }

    private Movie (Parcel in) {
        Title = in.readString();
        Date_Released = in.readString();
        RateMovie = in.readString();
        Poster_Path = in.readString();
        Description = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    String getTitle() { return Title;}

    String getDate_Released() { return Date_Released;}

    String getRate() { return RateMovie;}

    String getPoster_Path() { return Poster_Path;}

    String getDescription() { return Description;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Title);
        dest.writeString(Date_Released);
        dest.writeString(RateMovie);
        dest.writeString(Poster_Path);
        dest.writeString(Description);
    }
}
