package com.guil.popularmoviesapp.Room;

import com.guil.popularmoviesapp.Models.FavoriteMovies;
import com.guil.popularmoviesapp.MovieDetailsActivity;

public class QueryFavoriteState {
    private static final String TAG = "QueryFavoriteState";
    private static QueryFavoriteState instance;

    public static QueryFavoriteState getInstance() {
        if(instance == null) {
            instance = new QueryFavoriteState();
        }
        return instance;
    }

    public boolean isMovieInDB(String movieId) {
        for(FavoriteMovies movie : MovieDetailsActivity.mFavoriteMoviesInDB) {
            if(movieId.equals(movie.getId())) {
                return true;
            }
        }
        return false;
    }
}
