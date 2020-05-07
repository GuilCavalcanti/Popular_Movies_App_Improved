package com.guil.popularmoviesapp.Utils;

import android.content.Context;

public class MiddleManClass {

    private static MiddleManClass instance;
    private Context context;

    public static MiddleManClass getInstance() {
        if(instance == null) {
            instance = new MiddleManClass();
        }
        return instance;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
