package com.guil.popularmoviesapp.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.guil.popularmoviesapp.Models.FavoriteMovies;
import com.guil.popularmoviesapp.Models.PopularMovies;
import com.guil.popularmoviesapp.Models.TopRatedMovies;
import java.util.List;


@Dao
public interface MovieDAO {

    @Insert
    void insertPopularMovies(PopularMovies... movies);

    @Query("SELECT * FROM popular_movies")
    LiveData<List<PopularMovies>> getPopularMovies();

    @Insert
    void insertTopRatedMovies(TopRatedMovies... movies);

    @Query("SELECT * FROM top_rated_movies")
    LiveData<List<TopRatedMovies>> getTopRatedMovies();

    @Insert
    void insertFavoriteMovies(FavoriteMovies... movies);

    @Query("SELECT * FROM favorite_movies")
    LiveData<List<FavoriteMovies>> getFavoriteMovies();

    @Query("DELETE FROM favorite_movies WHERE id = :movieId")
    void delete(String movieId);

    @Query("DELETE FROM popular_movies")
    void deletePopularTable();

    @Query("DELETE FROM top_rated_movies")
    void deleteTopRatedTable();

    @Query("DELETE FROM favorite_movies")
    void deleteFavoriteTable();
}
