package com.guil.popularmoviesapp.Async;

import android.os.AsyncTask;
import android.util.Log;
import com.guil.popularmoviesapp.Models.FavoriteMovies;
import com.guil.popularmoviesapp.Room.MovieDAO;

public class InsertFavoriteMovies extends AsyncTask<FavoriteMovies, Void, Void> {

    private static final String TAG = "InsertFavoriteMovies";
    private MovieDAO mMovieDAO;

    public InsertFavoriteMovies(MovieDAO movieDAO) {
        mMovieDAO = movieDAO;
    }

    @Override
    protected Void doInBackground(FavoriteMovies... movies) {

        mMovieDAO.insertFavoriteMovies(movies[0]);
        Log.d(TAG, "Inserted Favorite movie: " + movies[0].getTitle());
        return null;
    }
}