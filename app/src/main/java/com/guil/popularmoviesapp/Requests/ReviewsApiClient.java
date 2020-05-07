package com.guil.popularmoviesapp.Requests;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.guil.popularmoviesapp.Models.Reviews;
import com.guil.popularmoviesapp.Requests.Responses.ReviewsResponse;
import com.guil.popularmoviesapp.Utils.BackgroundExecutors;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;
import static com.guil.popularmoviesapp.Utils.Constants.API_KEY;

public class ReviewsApiClient {

    private static final String TAG = "ReviewApiClient";
    private static ReviewsApiClient instance;
    private MutableLiveData<List<Reviews>> mReviews;
    private GetReviewsRunnable mGetReviewsRunnable;

    public static ReviewsApiClient getInstance() {
        if(instance == null) {
            instance = new ReviewsApiClient();
        }
        return instance;
    }

    private ReviewsApiClient() {
        mReviews = new MutableLiveData<>();
    }

    public MutableLiveData<List<Reviews>> getReviews() {
        return mReviews;
    }

    public void getReviewsApi(String movieId) {
        if(mGetReviewsRunnable != null) {
            mGetReviewsRunnable = null;
        }
        mReviews.postValue(null);
        mGetReviewsRunnable = new GetReviewsRunnable(movieId);
        BackgroundExecutors.getInstance().networkIO().submit(mGetReviewsRunnable);
    }

    private class GetReviewsRunnable implements Runnable {

        private String movieId;

        public GetReviewsRunnable(String movieId) {
            this.movieId = movieId;
        }

        @Override
        public void run() {
            try {
                Response response = getReviews(movieId).execute();
                if(response.code() == 200) {
                    List<Reviews> reviewsList = new ArrayList<>(((ReviewsResponse)response.body()).getReviews());
                    mReviews.postValue(reviewsList);
                } else {
                    String error = response.errorBody().string();
                    Log.e(TAG, "error: " + error);
                    mReviews.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mReviews.postValue(null);
            }
        }

        private Call<ReviewsResponse> getReviews(String movieId) {
            return ServiceGen.getMovieApi().reviews(
                    movieId,
                    API_KEY
            );
        }
    }
}
