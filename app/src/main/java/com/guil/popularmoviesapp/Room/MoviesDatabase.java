package com.guil.popularmoviesapp.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.guil.popularmoviesapp.Models.FavoriteMovies;
import com.guil.popularmoviesapp.Models.PopularMovies;
import com.guil.popularmoviesapp.Models.TopRatedMovies;

import static androidx.room.Room.databaseBuilder;

@Database(entities = {PopularMovies.class, FavoriteMovies.class, TopRatedMovies.class}, version = 1)
public abstract class MoviesDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "movies_db";
    private static MoviesDatabase instance;

    public static MoviesDatabase getInstance(final Context context) {
        if(instance == null) {
            instance = databaseBuilder(
                    context.getApplicationContext(),
                    MoviesDatabase.class,
                    DATABASE_NAME
            ).build();
        }
        return instance;
    }

    public abstract MovieDAO getMovieDAO();
}
