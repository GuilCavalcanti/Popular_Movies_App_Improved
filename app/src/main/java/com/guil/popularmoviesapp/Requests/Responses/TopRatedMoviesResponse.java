package com.guil.popularmoviesapp.Requests.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.guil.popularmoviesapp.Models.TopRatedMovies;
import java.util.List;

public class TopRatedMoviesResponse {

    @SerializedName("results")
    @Expose()
    private List<TopRatedMovies> movies;

    public List<TopRatedMovies> getMovies() {
        for (TopRatedMovies movie : movies) {
            movie.setInMovieDB("0");
        }
        return movies;
    }

    @Override
    public String toString() {
        return "TopRatedMoviesResponse{" +
                "movies=" + movies +
                '}';
    }
}