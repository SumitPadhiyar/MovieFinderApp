package com.demoapps.moviefinderapp.presentation;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demoapps.moviefinderapp.R;
import com.tokenautocomplete.TokenCompleteTextView;

/**
 * Created by Skip on 26-03-2017.
 */
public class MovieCompletionView extends TokenCompleteTextView<String> {

    public MovieCompletionView(Context context) {
        super(context);
    }

    public MovieCompletionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MovieCompletionView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected View getViewForObject(String object) {
        LayoutInflater l = (LayoutInflater)getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        TextView token = (TextView) l.inflate(R.layout.movie_token, (ViewGroup) getParent(), false);
        token.setText(object);
        return token;
    }

    @Override
    protected String defaultObject(String completionText) {
        return completionText.trim();
    }
}
