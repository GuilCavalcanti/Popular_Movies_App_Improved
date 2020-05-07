package com.guil.popularmoviesapp.Async;

import android.os.AsyncTask;
import android.util.Log;
import com.guil.popularmoviesapp.Room.MovieDAO;

public class DeleteFavoriteMovie extends AsyncTask<String, Void, Void> {

    private static final String TAG = "DeleteMovies";
    private MovieDAO mMovieDAO;
    private String mTitle;

    public DeleteFavoriteMovie(MovieDAO movieDAO, String title) {
        mMovieDAO = movieDAO;
        mTitle = title;
    }

    @Override
    protected Void doInBackground(String... movieId) {

        mMovieDAO.delete(movieId[0]);
        Log.d(TAG, "Removed: " + mTitle);
        return null;
    }
}