package com.demoapps.moviefinderapp.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Skip on 27-03-2017.
 */
public class MovieListingResponse implements JsonDeserializer<MovieListingResponse>{

    private static final String TAG_LOG = MovieListingResponse.class.getSimpleName();

    private boolean response;
    private String error;
    private Movie movie;

    private static final String JKEY_RESPONSE = "Response";
    private static final String JKEY_ERROR = "Error";

    @Override
    public MovieListingResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        if (json.isJsonNull() || !json.isJsonObject()) {
            return null;
        }

        JsonObject jsonObject = json.getAsJsonObject();
        MovieListingResponse movieListingResponse = new MovieListingResponse();
        movieListingResponse.response = jsonObject.get(JKEY_RESPONSE).getAsBoolean();
        if(movieListingResponse.isResponse()){
            movieListingResponse.movie = context.deserialize(json,Movie.class);
        }else{
            movieListingResponse.error = jsonObject.get(JKEY_ERROR).getAsString();
        }

        return movieListingResponse;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getError() {
        return error;
    }

    public boolean isResponse() {
        return response;
    }
}
