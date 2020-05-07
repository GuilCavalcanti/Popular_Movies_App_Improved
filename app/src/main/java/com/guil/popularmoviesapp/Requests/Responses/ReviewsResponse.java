package com.guil.popularmoviesapp.Requests.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.guil.popularmoviesapp.Models.Reviews;

import java.util.List;

public class ReviewsResponse {

    @SerializedName("total_results")
    @Expose()
    private int numberOfReviews;

    @SerializedName("results")
    @Expose()
    private List<Reviews> reviews;

    public int getNumberOfReviews() {
        return numberOfReviews;
    }

    public List<Reviews> getReviews() {
        return reviews;
    }

    @Override
    public String toString() {
        return "ReviewsResponse{" +
                "numberOfReviews=" + numberOfReviews +
                ", reviews=" + reviews +
                '}';
    }
}
