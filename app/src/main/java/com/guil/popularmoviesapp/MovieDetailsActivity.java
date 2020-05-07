package com.guil.popularmoviesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.guil.popularmoviesapp.Adapters.MoviesDetailsRecyclerView.ReviewAdapter;
import com.guil.popularmoviesapp.Adapters.MoviesDetailsRecyclerView.TrailerAdapter;
import com.guil.popularmoviesapp.Adapters.MoviesRecyclerView.MovieViewHolder;
import com.guil.popularmoviesapp.Models.FavoriteMovies;
import com.guil.popularmoviesapp.Models.PopularMovies;
import com.guil.popularmoviesapp.Models.TopRatedMovies;
import com.guil.popularmoviesapp.Models.Trailers;
import com.guil.popularmoviesapp.Repositores.MoviesRepositories;
import com.guil.popularmoviesapp.Room.QueryFavoriteState;
import com.guil.popularmoviesapp.ViewModels.MoviesViewModel;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.guil.popularmoviesapp.MainActivity.mFavoriteMoviesAdapter;
import static com.guil.popularmoviesapp.Utils.Constants.POSTER_BASE_URL;

public class MovieDetailsActivity extends AppCompatActivity {

    private static final String TAG = "MoviesDetailsActivity";
    public static List<FavoriteMovies> mFavoriteMoviesInDB;

    private ArrayList<PopularMovies> mPopularMovies;
    private ArrayList<TopRatedMovies> mTopRatedMovies;
    private ArrayList<FavoriteMovies> mFavoriteMovies;
    private int index;
    private int criteria;
    private String posterPath;
    private String movieId;

    //Instantiating LiveData
    private MoviesViewModel mMoviesViewModel;

    //Instantiating RecyclerView and Adapter (Trailer)
    private RecyclerView mRecyclerViewTrailer;
    private TrailerAdapter mAdapterTrailer;

    //Instantiating RecyclerView and Adapter (Review)
    private RecyclerView mRecyclerViewReview;
    private ReviewAdapter mAdapterReview;

    //Instantiating Repository
    private MoviesRepositories repositories;

    //Instantiating a new favorite movie
    private FavoriteMovies mNewFavoriteMovie;

    private QueryFavoriteState checkState;

    @BindView(R.id.movie_poster_details) ImageView moviePoster;
    @BindView(R.id.movie_title) TextView title;
    @BindView(R.id.movie_release_date) TextView releaseDate;
    @BindView(R.id.movie_vote_average) TextView rating;
    @BindView(R.id.movie_synopsis) TextView synopsis;
    @BindView(R.id.favorite) ImageView favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);

        mFavoriteMoviesInDB = new ArrayList<>();
        Intent intent = getIntent();
        criteria = intent.getIntExtra("criteria", 5);
        index = intent.getIntExtra("index", 55);
        movieId = null;
        checkState = QueryFavoriteState.getInstance();
        repositories = MoviesRepositories.getInstance();

        switch(criteria) {
            case 0:
                mPopularMovies = intent.getParcelableArrayListExtra("Popular Movies");
                title.setText(mPopularMovies.get(index).getTitle());
                releaseDate.setText(parseDate(mPopularMovies.get(index).getRelease_date()));
                rating.setText(String.valueOf(mPopularMovies.get(index).getVote_average()));
                synopsis.setText(mPopularMovies.get(index).getOverview());
                posterPath = mPopularMovies.get(index).getPoster_path();
                movieId = mPopularMovies.get(index).getId();

                if (checkState.isMovieInDB((mPopularMovies.get(index).getId()))) {
                    favorite.setImageResource(R.drawable.ic_star_black_24dp);
                } else {
                    favorite.setImageResource(R.drawable.ic_star_border_black_24dp);
                }
                break;

            case 1:
                mTopRatedMovies = intent.getParcelableArrayListExtra("Top Rated Movies");
                title.setText(mTopRatedMovies.get(index).getTitle());
                releaseDate.setText(parseDate(mTopRatedMovies.get(index).getRelease_date()));
                rating.setText(String.valueOf(mTopRatedMovies.get(index).getVote_average()));
                synopsis.setText(mTopRatedMovies.get(index).getOverview());
                posterPath = mTopRatedMovies.get(index).getPoster_path();
                movieId = mTopRatedMovies.get(index).getId();

                if (checkState.isMovieInDB((mTopRatedMovies.get(index).getId()))) {
                    favorite.setImageResource(R.drawable.ic_star_black_24dp);
                } else {
                    favorite.setImageResource(R.drawable.ic_star_border_black_24dp);
                }
                break;

            case 2:
                mFavoriteMovies = intent.getParcelableArrayListExtra("Favorite Movies");
                title.setText(mFavoriteMovies.get(index).getTitle());
                releaseDate.setText(mFavoriteMovies.get(index).getRelease_date());
                rating.setText(String.valueOf(mFavoriteMovies.get(index).getVote_average()));
                synopsis.setText(mFavoriteMovies.get(index).getOverview());
                posterPath = mFavoriteMovies.get(index).getPoster_path();
                movieId = mFavoriteMovies.get(index).getId();

                if (checkState.isMovieInDB((mFavoriteMovies.get(index).getId()))) {
                    favorite.setImageResource(R.drawable.ic_star_black_24dp);
                } else {
                    favorite.setImageResource(R.drawable.ic_star_border_black_24dp);
                }
                break;

            default:
                break;
        }

        getMoviePoster(posterPath);

        mRecyclerViewTrailer = findViewById(R.id.recycler_view_trailers);
        mRecyclerViewReview = findViewById(R.id.recycler_view_reviews);
        mMoviesViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);

        initRecyclerView();
        getTrailers();
        getReviews();
        subscribeObservers();
        favoriteDatabaseObservers();

        favorite.setOnClickListener(v -> {
            if(checkState.isMovieInDB(movieId)) {
                repositories.deleteFavoriteMovies(movieId, title.getText().toString());
                favorite.setImageResource(R.drawable.ic_star_border_black_24dp);
            } else {
                mNewFavoriteMovie = new FavoriteMovies(
                        posterPath,
                        movieId,
                        title.getText().toString(),
                        synopsis.getText().toString(),
                        releaseDate.getText().toString(),
                        rating.getText().toString(),
                        "1"
                );
                repositories.insertFavoriteMovie(mNewFavoriteMovie);
                favorite.setImageResource(R.drawable.ic_star_black_24dp);
            }
        });
    }

    private void favoriteDatabaseObservers() {
        mMoviesViewModel.getFavoriteMovies().observe(this, favoriteMovies -> {

            mFavoriteMoviesInDB.clear();
            for(FavoriteMovies movie : favoriteMovies) {
                Log.e(TAG, "Movies in Favorites Database: " + movie.getTitle());
            }
            mFavoriteMoviesInDB.addAll(favoriteMovies);
            mFavoriteMoviesAdapter.setMovies(favoriteMovies);
            mFavoriteMoviesAdapter.notifyDataSetChanged();

            if (checkState.isMovieInDB(movieId)) {
                favorite.setImageResource(R.drawable.ic_star_black_24dp);
            } else {
                favorite.setImageResource(R.drawable.ic_star_border_black_24dp);
            }
        });
    }

    private String parseDate(String date) {

        Log.e(TAG, "date: " + date);

        String[] splitDate = date.split("-");
        return splitDate[2] + "/" + splitDate[1] + "/" + splitDate[0];
    }

    private void getMoviePoster(String posterPath) {

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background);
        Glide.with(this)
                .load(POSTER_BASE_URL + posterPath)
                .apply(requestOptions)
                .into(moviePoster);
    }

    private void initRecyclerView() {
        mAdapterTrailer = new TrailerAdapter();
        mRecyclerViewTrailer.setAdapter(mAdapterTrailer);
        mRecyclerViewTrailer.setLayoutManager(new LinearLayoutManager(this));

        mAdapterReview = new ReviewAdapter();
        mRecyclerViewReview.setAdapter(mAdapterReview);
        mRecyclerViewReview.setLayoutManager(new LinearLayoutManager(this));
    }

    private void subscribeObservers(){
        mMoviesViewModel.getTrailers().observe(this, trailers -> {
            if(trailers != null) {
                mAdapterTrailer.setTrailers(trailers);
            } else {
                mAdapterTrailer.setTrailers(null);
            }
        });
        mMoviesViewModel.getReviews().observe(this, reviews -> {
            if(reviews != null) {
                mAdapterReview.setReviews(reviews);
            } else {
                mAdapterReview.setReviews(null);
            }
        });
    }

    private void getTrailers() {
        mMoviesViewModel.getTrailersApi(movieId);
    }
    private void getReviews() {
        mMoviesViewModel.getReviewsApi(movieId);
    }
}
