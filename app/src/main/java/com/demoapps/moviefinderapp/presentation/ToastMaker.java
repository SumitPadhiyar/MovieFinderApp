package com.demoapps.moviefinderapp.presentation;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Skip on 02-04-2017.
 */
public class ToastMaker {

    private static Toast toast;

    public static void make(
            Context context,
            CharSequence text,
            int duration) {
        if (null == toast) {
            toast = Toast.makeText(context, text, duration);
        } else {
            toast.setText(text);
            toast.setDuration(duration);
        }
        toast.show();
    }

    public static void make(
            Context context,
            int resId,
            int duration) {
        make(context, context.getString(resId), duration);
    }
}