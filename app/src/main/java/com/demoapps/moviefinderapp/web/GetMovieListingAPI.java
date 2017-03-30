package com.demoapps.moviefinderapp.web;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.demoapps.moviefinderapp.R;
import com.demoapps.moviefinderapp.model.MovieListingResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Skip on 27-03-2017.
 */
public class GetMovieListingApi {

    private static final String TAG_LOG = GetMovieListingApi.class.getSimpleName();

    private final Context ctx;
    private final MovieListingService service;
    private final GetMovieListingApiCallback apiCallbackListener;
    private HashMap<String,String> queryMap;
    private MovieListingResponse apiResponse;

    public GetMovieListingApi(Context ctx, GetMovieListingApiCallback callBackListener) {

        this.ctx = ctx;
        OkHttpClient client = new OkHttpClient();

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://www.omdbapi.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(MovieListingService.class);
        queryMap = new HashMap<>();
        queryMap.put("plot", "short");
        queryMap.put("r", "json");

        this.apiCallbackListener = callBackListener;
    }

    //region Instance Methods

    public void getMovieListing(String title, String type) {

        if (!isNetworkConnected()) {
            apiCallbackListener.onResponse(true, ctx.getString(R.string.msg__err__no_network));
            return;
        }

        queryMap.put("t",title);
        queryMap.put("type",type);

        Call<MovieListingResponse> call = (Call<MovieListingResponse>) service.getMovieListingResponse(queryMap);
        call.enqueue(new Callback<MovieListingResponse>() {
            @Override
            public void onResponse(Call<MovieListingResponse> call, Response<MovieListingResponse> response) {
                Log.v(TAG_LOG, "onResponse:  IN");

                if (!response.isSuccessful()) {
                    apiCallbackListener.onResponse(true, ctx.getString(R.string.msg__err__something_wrong));
                    return;
                }

                Log.v(TAG_LOG, "onResponse:  response" + response.raw());

                apiResponse = response.body();

                apiCallbackListener.onResponse(false, null);

                Log.v(TAG_LOG, "onResponse:  OUT");
            }

            @Override
            public void onFailure(Call<MovieListingResponse> call, Throwable t) {
                Log.v(TAG_LOG, "onFailure:  IN");
                t.printStackTrace();
                apiCallbackListener.onResponse(true, ctx.getString(R.string.msg__err__something_wrong));
                Log.v(TAG_LOG, "onFailure:  OUT");
            }
        });

    }

    public MovieListingResponse getApiResponse() {
        return apiResponse;
    }

    private boolean isNetworkConnected() {
        NetworkInfo networkInfo = ((ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }


    //endregion

    //region Interfaces
    public interface GetMovieListingApiCallback {
        void onResponse(boolean isError, String errMsg);
    }
    //endregion
}
