package com.demoapps.moviefinderapp.presentation;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Skip on 26-03-2017.
 */
public class MovieFinderApp extends Application {
    private static final String TAG_LOG = MovieFinderApp.class.getSimpleName();

    public boolean isNetworkConnected() {
        NetworkInfo networkInfo = ((ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
