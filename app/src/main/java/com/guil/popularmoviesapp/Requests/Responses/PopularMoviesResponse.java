package com.guil.popularmoviesapp.Requests.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.guil.popularmoviesapp.Models.PopularMovies;

import java.util.List;

public class PopularMoviesResponse {

    @SerializedName("results")
    @Expose()
    private List<PopularMovies> movies;

    public List<PopularMovies> getMovies() {
        for (PopularMovies movie : movies) {
            movie.setInMovieDB("0");
        }
        return movies;
    }

    @Override
    public String toString() {
        return "PopularMoviesResponse{" +
                "movies=" + movies +
                '}';
    }
}