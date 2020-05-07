package com.guil.popularmoviesapp.Requests;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.guil.popularmoviesapp.Models.TopRatedMovies;
import com.guil.popularmoviesapp.Requests.Responses.TopRatedMoviesResponse;
import com.guil.popularmoviesapp.Room.MoviesDatabase;
import com.guil.popularmoviesapp.Utils.BackgroundExecutors;
import com.guil.popularmoviesapp.Utils.MiddleManClass;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static com.guil.popularmoviesapp.Utils.Constants.API_KEY;

public class TopRatedMoviesApiClient {

    private MoviesDatabase mMoviesDatabase = MoviesDatabase.getInstance(MiddleManClass.getInstance().getContext());

    private static final String TAG = "TopRatedMoviesApiClient";

    private static TopRatedMoviesApiClient instance;
    private MutableLiveData<List<TopRatedMovies>> mMovies;
    private GetMoviesRunnable mGetMoviesRunnable;

    public static TopRatedMoviesApiClient getInstance() {
        if(instance == null) {
            instance = new TopRatedMoviesApiClient();
        }
        return instance;
    }

    private TopRatedMoviesApiClient() {
        mMovies = new MutableLiveData<>();
    }

    public MutableLiveData<List<TopRatedMovies>> getMovies(){
        return mMovies;
    }

    public void clearMovies() {
        mMovies.postValue(null);
    }

    public void getMoviesApi() {
        if(mGetMoviesRunnable != null) {
            mGetMoviesRunnable = null;
            mMovies.postValue(null);
        }
        mGetMoviesRunnable = new GetMoviesRunnable();
        BackgroundExecutors.getInstance().networkIO().submit(mGetMoviesRunnable);
    }

    //Runnable method to get the movies from the API
    private class GetMoviesRunnable implements Runnable {

        private String criteria;

        public GetMoviesRunnable() {

        }

        @Override
        public void run() {
            try {
                Response response = getMovies(criteria).execute();
                if(response.code() == 200) {
                    List<TopRatedMovies> moviesList = new ArrayList<>(((TopRatedMoviesResponse)response.body()).getMovies());
                    mMovies.postValue(moviesList);
                    for(TopRatedMovies movie : moviesList) {
                        mMoviesDatabase.getMovieDAO().insertTopRatedMovies(movie);
                    }
                } else {
                    String error = response.errorBody().string();
                    Log.e(TAG, "error: " + error);
                    mMovies.postValue(null);
                }
            } catch (Exception e) {
                e.printStackTrace();
                mMovies.postValue(null);
            }
        }

        private Call<TopRatedMoviesResponse> getMovies(String criteria) {
            return ServiceGen.getMovieApi().topRatedMovies(
                    API_KEY
            );
        }
    }
}

