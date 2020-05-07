package com.guil.popularmoviesapp.Async;

import android.os.AsyncTask;

import com.guil.popularmoviesapp.Repositores.MoviesRepositories;

public class DeleteTables extends AsyncTask<Void, Void, Void> {

    private MoviesRepositories mMoviesRepositories = MoviesRepositories.getInstance();

    public DeleteTables() {

    }

    @Override
    protected Void doInBackground(Void... voids) {
        mMoviesRepositories.deleteTable();
        return null;
    }
}
