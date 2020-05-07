package com.guil.popularmoviesapp.Requests;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.guil.popularmoviesapp.Models.Trailers;
import com.guil.popularmoviesapp.Requests.Responses.TrailersResponse;
import com.guil.popularmoviesapp.Utils.BackgroundExecutors;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;
import static com.guil.popularmoviesapp.Utils.Constants.API_KEY;

public class TrailerApiClient {
    //TAG
    private static final String TAG = "TrailerApiClient";

    private static TrailerApiClient instance;
    private MutableLiveData<List<Trailers>> mTrailers;
    private GetTrailersRunnable mGetTrailersRunnable;

    public static TrailerApiClient getInstance() {
        if(instance == null) {
            instance = new TrailerApiClient();
        }
        return instance;
    }

    private TrailerApiClient() {
        mTrailers = new MutableLiveData<>();
    }

    public MutableLiveData<List<Trailers>> getTrailers() {
        return mTrailers;
    }

    public void getTrailersApi(String movieId) {
        if(mGetTrailersRunnable != null) {
            mGetTrailersRunnable = null;
        }
        mTrailers.postValue(null);
        mGetTrailersRunnable = new GetTrailersRunnable(movieId);
        BackgroundExecutors.getInstance().networkIO().submit(mGetTrailersRunnable);
    }

    private class GetTrailersRunnable implements Runnable {

        private String movieId;

        public GetTrailersRunnable(String movieId) {
            this.movieId = movieId;
        }

        @Override
        public void run() {
            try {
                Response response = getTrailers(movieId).execute();
                if(response.code() == 200) {
                    List<Trailers> trailersList = new ArrayList<>(((TrailersResponse)response.body()).getTrailers());
                    mTrailers.postValue(trailersList);
                } else {
                    String error = response.errorBody().string();
                    Log.e(TAG, "error: " + error);
                    mTrailers.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mTrailers.postValue(null);
            }
        }

        private Call<TrailersResponse> getTrailers(String movieId) {
            return ServiceGen.getMovieApi().trailers(
                    movieId,
                    API_KEY
            );
        }
    }
}
