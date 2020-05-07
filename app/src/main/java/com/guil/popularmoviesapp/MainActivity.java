package com.guil.popularmoviesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.guil.popularmoviesapp.Adapters.FragmentAdapter.FragmentAdapter;
import com.guil.popularmoviesapp.Adapters.MoviesRecyclerView.FavoriteMoviesAdapter;
import com.guil.popularmoviesapp.Adapters.MoviesRecyclerView.PopularMoviesAdapter;
import com.guil.popularmoviesapp.Adapters.MoviesRecyclerView.TopRatedMoviesAdapter;
import com.guil.popularmoviesapp.Async.DeleteTables;
import com.guil.popularmoviesapp.Fragments.FavoriteMoviesFragment;
import com.guil.popularmoviesapp.Fragments.PopularMoviesFragment;
import com.guil.popularmoviesapp.Fragments.TopRatedMoviesFragment;
import com.guil.popularmoviesapp.Models.FavoriteMovies;
import com.guil.popularmoviesapp.Models.PopularMovies;
import com.guil.popularmoviesapp.Models.TopRatedMovies;
import com.guil.popularmoviesapp.Utils.MiddleManClass;
import com.guil.popularmoviesapp.ViewModels.MoviesViewModel;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    //public static List<FavoriteMovies> mFavoriteMovies;
    public static String connectionState;

    public static Button reloadButton;
    public static TextView connectionErrorTv;

    // MainActivity TAG
    private static final String TAG = "MainActivity";

    //Instantiating ViewModel
    private MoviesViewModel mMoviesViewModel;

    //Instantiating RecyclerViews and Adapters
    public static PopularMoviesAdapter mPopularMoviesAdapter;
    public static TopRatedMoviesAdapter mTopRatedMoviesAdapter;
    public static FavoriteMoviesAdapter mFavoriteMoviesAdapter;

    //Fragments and Fragments related
    private PopularMoviesFragment mPopularFragment;
    private TopRatedMoviesFragment mTopRatedFragment;
    private FavoriteMoviesFragment mFavoriteFragment;

    private ViewPager2 mViewPager2;
    private FragmentAdapter mFragmentAdapter;

    //Delete Popular and Top Rated Tables
    private DeleteTables deleteTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);

        //Variable to check Connection/Size of Popular Movies in Database (which are used to set the adapter data)
        connectionState = "0";

        //mFavoriteMovies = new ArrayList<>();
        MiddleManClass.getInstance().setContext(this);
        mMoviesViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);

        mMoviesViewModel.getPopularMoviesApi();
        mMoviesViewModel.getTopRatedMoviesApi();

        reloadButton = findViewById(R.id.reload_button);
        connectionErrorTv = findViewById(R.id.connection_error_tv);

        reloadButton.setOnClickListener(v -> {
            mMoviesViewModel.getPopularMoviesApi();
            mMoviesViewModel.getTopRatedMoviesApi();
        });

        initRecyclerViewAdapters();
        printDatabase();
        subscribeObservers();

        mPopularFragment = new PopularMoviesFragment();
        mTopRatedFragment = new TopRatedMoviesFragment();
        mFavoriteFragment = new FavoriteMoviesFragment();

        mViewPager2 = findViewById(R.id.container);
        mFragmentAdapter = new FragmentAdapter(this);
        addFragments();

        deleteTable = new DeleteTables();
        deleteTable.execute();

    }

    public static void hideConnectionMessage() {
        reloadButton.setVisibility(View.INVISIBLE);
        connectionErrorTv.setVisibility(View.INVISIBLE);
    }

    private void addFragments() {
        mFragmentAdapter.addFragments(mPopularFragment);
        mFragmentAdapter.addFragments(mTopRatedFragment);
        mFragmentAdapter.addFragments(mFavoriteFragment);
        mViewPager2.setAdapter(mFragmentAdapter);
    }


    private void initRecyclerViewAdapters() {
        mPopularMoviesAdapter = new PopularMoviesAdapter();
        mTopRatedMoviesAdapter = new TopRatedMoviesAdapter();
        mFavoriteMoviesAdapter = new FavoriteMoviesAdapter();
    }

    //Function to Retrieve initial data from Database (if connection to API was successful) and feed it to the RecyclerView Adapters
    private void subscribeObservers() {
        mMoviesViewModel.retrievePopularMovies().observe(this, popularMovies -> {
            if(connectionState.equals("1")) {
                hideConnectionMessage();
            }
            mPopularMoviesAdapter.setMovies(popularMovies);
            mPopularMoviesAdapter.notifyDataSetChanged();
        });
        mMoviesViewModel.retrieveTopRatedMovies().observe(this, topRatedMovies -> {
            mTopRatedMoviesAdapter.setMovies(topRatedMovies);
            mTopRatedMoviesAdapter.notifyDataSetChanged();
        });
        mMoviesViewModel.getFavoriteMovies().observe(this, favoriteMovies -> {

            mFavoriteMoviesAdapter.setMovies(favoriteMovies);
            mFavoriteMoviesAdapter.notifyDataSetChanged();
        });
    }

    public void printDatabase() {
        mMoviesViewModel.retrievePopularMovies().observe(this, popularMovies -> {
            for(PopularMovies movie : popularMovies) {
                Log.e(TAG, "On Popular Database: name " + movie.getTitle());
            }
        });
        mMoviesViewModel.retrieveTopRatedMovies().observe(this, topRatedMovies -> {
            for(TopRatedMovies movie : topRatedMovies) {
                Log.e(TAG, "On Top Rated Database: name " + movie.getTitle());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_sort_by_popularity:
                mViewPager2.setCurrentItem(0);
                return true;

            case R.id.action_sort_by_rating:
                mViewPager2.setCurrentItem(1);
                return true;

            case R.id.action_sort_by_favorite:
                mViewPager2.setCurrentItem(2);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.category_search_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
