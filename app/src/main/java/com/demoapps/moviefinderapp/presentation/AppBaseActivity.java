package com.demoapps.moviefinderapp.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Skip on 01-04-2017.
 */
public abstract class AppBaseActivity extends AppCompatActivity {

    private static final String TAG_LOG = AppBaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onParseArgs();
        onSetupUI(savedInstanceState);
    }
    protected void onParseArgs() {
    }

    abstract void onSetupUI(Bundle savedInstanceState);

}
