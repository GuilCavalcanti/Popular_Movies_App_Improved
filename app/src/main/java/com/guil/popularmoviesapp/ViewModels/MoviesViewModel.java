package com.guil.popularmoviesapp.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.guil.popularmoviesapp.Models.FavoriteMovies;
import com.guil.popularmoviesapp.Models.PopularMovies;
import com.guil.popularmoviesapp.Models.Reviews;
import com.guil.popularmoviesapp.Models.TopRatedMovies;
import com.guil.popularmoviesapp.Models.Trailers;
import com.guil.popularmoviesapp.Repositores.MoviesRepositories;

import java.util.List;

public class MoviesViewModel extends ViewModel {

    private MoviesRepositories mMoviesRepositories;
    public MoviesViewModel() {
        mMoviesRepositories = MoviesRepositories.getInstance();
    }

    public void getPopularMoviesApi() {
        mMoviesRepositories.getPopularMoviesApi();
    }

    public void getTopRatedMoviesApi() {
        mMoviesRepositories.getTopRatedMoviesApi();
    }

    public void getTrailersApi(String movieId) { mMoviesRepositories.getTrailerApi(movieId); }
    public void getReviewsApi(String movieId) { mMoviesRepositories.getReviewApi(movieId); }

    public LiveData<List<Trailers>> getTrailers() { return mMoviesRepositories.getTrailers(); }
    public LiveData<List<Reviews>> getReviews() { return mMoviesRepositories.getReviews(); }

    //Code Used to Update UI using data from the API
    /*
    public MutableLiveData<List<PopularMovies>> getPopularMovies() {
        return mMoviesRepositories.getPopularMovies();
    }

    public MutableLiveData<List<TopRatedMovies>> getTopRatedMovies() {
        return mMoviesRepositories.getTopRatedMovies();
    }*/

    //Code Used to Update UI using data from the Database (the data in the DB came from the API)
    public LiveData<List<FavoriteMovies>> getFavoriteMovies() {
        return mMoviesRepositories.getFavoriteMovies();
    }

    //Code Used to Update UI using data from the Database (the data in the DB came from the API)
    public LiveData<List<PopularMovies>> retrievePopularMovies() {
        return mMoviesRepositories.retrievePopularMovies();
    }

    //Code Used to Update UI using data from the Database (the data in the DB came from the API)
    public LiveData<List<TopRatedMovies>> retrieveTopRatedMovies() {
        return mMoviesRepositories.retrieveTopRatedMovies();
    }
}
