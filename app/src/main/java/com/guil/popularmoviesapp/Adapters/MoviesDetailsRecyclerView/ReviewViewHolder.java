package com.guil.popularmoviesapp.Adapters.MoviesDetailsRecyclerView;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guil.popularmoviesapp.R;

public class ReviewViewHolder extends RecyclerView.ViewHolder{

    public TextView authorName;
    public TextView review;
    public View cardViewReview;

    public ReviewViewHolder(@NonNull View itemView) {
        super(itemView);
        authorName = itemView.findViewById(R.id.review_author);
        review = itemView.findViewById(R.id.review);
        cardViewReview = itemView.findViewById(R.id.cardViewBottom);
    }
}
