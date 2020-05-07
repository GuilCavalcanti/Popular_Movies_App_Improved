package com.guil.popularmoviesapp.Requests.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.guil.popularmoviesapp.Models.Trailers;

import java.util.List;

public class TrailersResponse {

    @SerializedName("results")
    @Expose
    private List<Trailers> trailers;

    public List<Trailers> getTrailers() {
        return trailers;
    }

    @Override
    public String toString() {
        return "TrailersResponse{" +
                "trailers=" + trailers +
                '}';
    }
}
