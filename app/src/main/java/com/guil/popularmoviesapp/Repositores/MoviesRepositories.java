package com.guil.popularmoviesapp.Repositores;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.guil.popularmoviesapp.Async.DeleteFavoriteMovie;
import com.guil.popularmoviesapp.Async.InsertFavoriteMovies;
import com.guil.popularmoviesapp.Models.FavoriteMovies;
import com.guil.popularmoviesapp.Models.PopularMovies;
import com.guil.popularmoviesapp.Models.Reviews;
import com.guil.popularmoviesapp.Models.TopRatedMovies;
import com.guil.popularmoviesapp.Models.Trailers;
import com.guil.popularmoviesapp.Requests.PopularMoviesApiClient;
import com.guil.popularmoviesapp.Requests.ReviewsApiClient;
import com.guil.popularmoviesapp.Requests.TopRatedMoviesApiClient;
import com.guil.popularmoviesapp.Requests.TrailerApiClient;
import com.guil.popularmoviesapp.Room.MoviesDatabase;
import com.guil.popularmoviesapp.Utils.MiddleManClass;

import java.util.List;

public class MoviesRepositories {

    private static MoviesRepositories instance;
    private PopularMoviesApiClient mPopularMoviesApiClient;
    private TopRatedMoviesApiClient mTopRatedMoviesApiClient;
    private MoviesDatabase mMoviesDatabase;
    private TrailerApiClient mTrailerApiClient;
    private ReviewsApiClient mReviewApiClient;

    public static MoviesRepositories getInstance() {
        if(instance == null) {
            instance = new MoviesRepositories();
        }
        return instance;
    }

    private MoviesRepositories() {

        mPopularMoviesApiClient = PopularMoviesApiClient.getInstance();
        mTopRatedMoviesApiClient = TopRatedMoviesApiClient.getInstance();
        mMoviesDatabase = MoviesDatabase.getInstance(MiddleManClass.getInstance().getContext());
        mTrailerApiClient = TrailerApiClient.getInstance();
        mReviewApiClient = ReviewsApiClient.getInstance();
    }

    public void getPopularMoviesApi() {
        mPopularMoviesApiClient.getMoviesApi();
    }
    public void getTopRatedMoviesApi() { mTopRatedMoviesApiClient.getMoviesApi(); }

    public void getTrailerApi(String movieId) { mTrailerApiClient.getTrailersApi(movieId); }
    public void getReviewApi(String movieId) { mReviewApiClient.getReviewsApi(movieId); }

    public LiveData<List<Trailers>> getTrailers() { return mTrailerApiClient.getTrailers(); }
    public LiveData<List<Reviews>> getReviews() { return  mReviewApiClient.getReviews(); }

    //Code Used to Update UI using data from the API
    /*
    public MutableLiveData<List<PopularMovies>> getPopularMovies() {
        return mPopularMoviesApiClient.getMovies();
    }

    public MutableLiveData<List<TopRatedMovies>> getTopRatedMovies() {
        return mTopRatedMoviesApiClient.getMovies();
    }*/

    //Code Used to Update UI using data from the Database (the data in the DB came from the API)
    public LiveData<List<FavoriteMovies>> getFavoriteMovies() {
        return mMoviesDatabase.getMovieDAO().getFavoriteMovies();
    }

    //Code Used to Update UI using data from the Database (the data in the DB came from the API)
    public LiveData<List<PopularMovies>> retrievePopularMovies() {
        return mMoviesDatabase.getMovieDAO().getPopularMovies();
    }

    //Code Used to Update UI using data from the Database (the data in the DB came from the API)
    public LiveData<List<TopRatedMovies>> retrieveTopRatedMovies() {
        return mMoviesDatabase.getMovieDAO().getTopRatedMovies();
    }

    public void insertFavoriteMovie(FavoriteMovies movies) {
        new InsertFavoriteMovies(mMoviesDatabase.getMovieDAO()).execute(movies);

    }

    public void deleteFavoriteMovies(String movieId, String movieTitle) {
        new DeleteFavoriteMovie(mMoviesDatabase.getMovieDAO(), movieTitle).execute(movieId);
    }

    public void deleteTable() {
        mMoviesDatabase.getMovieDAO().deletePopularTable();
        mMoviesDatabase.getMovieDAO().deleteTopRatedTable();
    }
}
