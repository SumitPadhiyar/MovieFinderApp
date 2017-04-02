package com.demoapps.moviefinderapp.web;

import com.demoapps.moviefinderapp.model.MovieListingResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Skip on 27-03-2017.
 */

/*
* RESTful service to fetch list of movies.
* */
public interface MovieListingService {

    @GET(".")
    Observable<MovieListingResponse> getMovieListingResponse(@QueryMap(encoded=true) Map<String,String> filters);
}
