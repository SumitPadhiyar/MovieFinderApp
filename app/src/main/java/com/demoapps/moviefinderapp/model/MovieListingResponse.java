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

    private boolean response;
    private String error;
    private Movie movie;

    private static final String JKEY_RESPONSE = "response";
    private static final String JKEY_ERROR = "Error";

    @Override
    public MovieListingResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        if (json.isJsonNull() || !json.isJsonObject()) {
            return null;
        }

        JsonObject jsonObject = json.getAsJsonObject();
        MovieListingResponse movieListingResponse = new MovieListingResponse();
        movieListingResponse.response = jsonObject.getAsJsonPrimitive(JKEY_RESPONSE).getAsBoolean();
        if(jsonObject.has(JKEY_ERROR)){
            movieListingResponse.error = jsonObject.get(JKEY_ERROR).getAsString();
        }else{
            movie = context.deserialize(json,Movie.class);
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
