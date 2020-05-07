package com.guil.popularmoviesapp.Requests;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.guil.popularmoviesapp.MainActivity;
import com.guil.popularmoviesapp.Models.PopularMovies;
import com.guil.popularmoviesapp.Requests.Responses.PopularMoviesResponse;
import com.guil.popularmoviesapp.Room.MoviesDatabase;
import com.guil.popularmoviesapp.Utils.BackgroundExecutors;
import com.guil.popularmoviesapp.Utils.MiddleManClass;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;
import static com.guil.popularmoviesapp.Utils.Constants.API_KEY;

public class PopularMoviesApiClient {

    private MoviesDatabase mMoviesDatabase = MoviesDatabase.getInstance(MiddleManClass.getInstance().getContext());

    private static final String TAG = "PopularMoviesApiClient";

    private static PopularMoviesApiClient instance;
    private MutableLiveData<List<PopularMovies>> mMovies;
    private GetMoviesRunnable mGetMoviesRunnable;

    public static PopularMoviesApiClient getInstance() {
        if(instance == null) {
            instance = new PopularMoviesApiClient();
        }
        return instance;
    }

    private PopularMoviesApiClient() {
        mMovies = new MutableLiveData<>();
    }

    public MutableLiveData<List<PopularMovies>> getMovies(){
        return mMovies;
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
                Response response = getMovies().execute();
                if(response.code() == 200) {
                    List<PopularMovies> moviesList = new ArrayList<>(((PopularMoviesResponse)response.body()).getMovies());
                    mMovies.postValue(moviesList);
                    for(PopularMovies movie : moviesList) {
                        mMoviesDatabase.getMovieDAO().insertPopularMovies(movie);
                    }
                    MainActivity.connectionState = "1";
                    //reloadButton.setVisibility(View.INVISIBLE);
                    //hideConnectionMessage();
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

        private Call<PopularMoviesResponse> getMovies() {
            return ServiceGen.getMovieApi().popularMovies(
                    API_KEY
            );
        }
    }
}

