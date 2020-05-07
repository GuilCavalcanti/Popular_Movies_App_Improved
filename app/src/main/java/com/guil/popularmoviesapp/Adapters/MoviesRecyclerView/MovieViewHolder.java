package com.guil.popularmoviesapp.Adapters.MoviesRecyclerView;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.guil.popularmoviesapp.R;

public class MovieViewHolder  extends RecyclerView.ViewHolder {

    AppCompatImageView poster;
    //ImageView favorite;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);

        poster = itemView.findViewById(R.id.movie_poster);
        //favorite = itemView.findViewById(R.id.favorite);
    }
}
