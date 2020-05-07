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
import com.guil.popularmoviesapp.Models.PopularMovies;
import com.guil.popularmoviesapp.MovieDetailsActivity;
import com.guil.popularmoviesapp.R;
import com.guil.popularmoviesapp.Repositores.MoviesRepositories;
import com.guil.popularmoviesapp.Room.QueryFavoriteState;
import java.util.ArrayList;
import java.util.List;
import static com.guil.popularmoviesapp.Utils.Constants.POSTER_BASE_URL;

public class PopularMoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Intent intent;
    private List<PopularMovies> mPopularMovies;

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
                .load(POSTER_BASE_URL + mPopularMovies.get(position).getPoster_path())
                .into(((MovieViewHolder)holder).poster);

        ((MovieViewHolder)holder).poster.setOnClickListener(v -> {

            intent = new Intent(v.getContext(), MovieDetailsActivity.class);
            intent.putParcelableArrayListExtra("Popular Movies", (ArrayList<PopularMovies>)mPopularMovies);
            intent.putExtra("index", position);
            intent.putExtra("criteria", 0);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if(mPopularMovies != null) {
            return mPopularMovies.size();
        }
        return 0;
    }

    public void setMovies(List<PopularMovies> movies) {
        if(movies != null) {
            mPopularMovies = movies;
        }
    }
}
