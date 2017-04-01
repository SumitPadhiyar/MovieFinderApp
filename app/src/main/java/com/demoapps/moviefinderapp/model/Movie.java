package com.demoapps.moviefinderapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Skip on 27-03-2017.
 */
public class Movie implements Parcelable {

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {

        public Movie createFromParcel(
                Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(
                int size) {
            return new Movie[size];
        }
    };

    @SerializedName("Title")
    private String title;
    @SerializedName("Genre")
    private String genre;
    @SerializedName("Released")
    private String releaseDate;
    @SerializedName("Plot")
    private String plot;
    @SerializedName("imdbRating")
    private String rating;


    public Movie(
            Parcel in) {

        String[] stringData = new String[5];
        in.readStringArray(stringData);
        title = stringData[0];
        genre = stringData[1];
        releaseDate = stringData[2];
        plot = stringData[3];
        rating = stringData[4];

    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPlot() {
        return plot;
    }

    public String getRating() {
        return rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{title,genre,releaseDate,plot,rating});
    }
}
