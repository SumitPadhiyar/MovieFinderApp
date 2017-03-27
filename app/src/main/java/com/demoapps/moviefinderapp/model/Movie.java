package com.demoapps.moviefinderapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Skip on 27-03-2017.
 */
public class Movie {

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
}
