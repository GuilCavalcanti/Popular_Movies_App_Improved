package com.guil.popularmoviesapp.Adapters.MoviesRecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.guil.popularmoviesapp.Models.FavoriteMovies;
import com.guil.popularmoviesapp.Models.TopRatedMovies;
import com.guil.popularmoviesapp.MovieDetailsActivity;
import com.guil.popularmoviesapp.R;
import com.guil.popularmoviesapp.Repositores.MoviesRepositories;
import com.guil.popularmoviesapp.Room.QueryFavoriteState;
import java.util.ArrayList;
import java.util.List;
import static com.guil.popularmoviesapp.Utils.Constants.POSTER_BASE_URL;

public class TopRatedMoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Intent intent;
    private List<TopRatedMovies> mTopRatedMovies;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_layout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background);

        Glide.with(holder.itemView.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(POSTER_BASE_URL + mTopRatedMovies.get(position).getPoster_path())
                .into(((MovieViewHolder)holder).poster);

        ((MovieViewHolder)holder).poster.setOnClickListener(v -> {

            intent = new Intent(v.getContext(), MovieDetailsActivity.class);
            intent.putParcelableArrayListExtra("Top Rated Movies", (ArrayList<TopRatedMovies>)mTopRatedMovies);
            intent.putExtra("index", position);
            intent.putExtra("criteria", 1);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if(mTopRatedMovies != null) {
            return mTopRatedMovies.size();
        }
        return 0;
    }

    public void setMovies(List<TopRatedMovies> movies) {
        if(movies != null) {
            mTopRatedMovies = movies;
        }
    }
}
