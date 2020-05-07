package com.guil.popularmoviesapp.Requests;

import com.guil.popularmoviesapp.Requests.Responses.PopularMoviesResponse;
import com.guil.popularmoviesapp.Requests.Responses.ReviewsResponse;
import com.guil.popularmoviesapp.Requests.Responses.TopRatedMoviesResponse;
import com.guil.popularmoviesapp.Requests.Responses.TrailersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    //Get Movies from API
    @GET("/3/movie/popular")
    Call<PopularMoviesResponse> popularMovies(
            @Query("api_key") String key);

    //Get Movies from API
    @GET("/3/movie/top_rated")
    Call<TopRatedMoviesResponse> topRatedMovies(
            @Query("api_key") String key);

    //Get Trailers for a specific movie from the API
    @GET("/3/movie/{movie_id}/videos")
    Call<TrailersResponse> trailers(
            @Path("movie_id") String movieID,
            @Query("api_key") String key
    );

    //Get Reviews for a specific movie from the API
    @GET("/3/movie/{movie_id}/reviews")
    Call<ReviewsResponse> reviews(
            @Path("movie_id") String movieID,
            @Query("api_key") String key
    );
}

