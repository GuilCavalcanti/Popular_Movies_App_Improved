package com.guil.popularmoviesapp.Adapters.MoviesDetailsRecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.guil.popularmoviesapp.Models.Reviews;
import com.guil.popularmoviesapp.R;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Reviews> mReviews;
    private boolean emptyList;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reviews_layout, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(emptyList) {
            ((ReviewViewHolder)holder).cardViewReview.setVisibility(View.GONE);
        } else {
            ((ReviewViewHolder)holder).cardViewReview.setVisibility(View.VISIBLE);
            ((ReviewViewHolder)holder).authorName.setText(mReviews.get(position).getAuthor());
            ((ReviewViewHolder)holder).review.setText(mReviews.get(position).getContent());
        }
    }

    @Override
    public int getItemCount() {
        emptyList = false;
        if(mReviews == null || mReviews.size() == 0) {
            emptyList = true;
            return 1;
        } else {
            return mReviews.size();
        }
    }

    public void setReviews(List<Reviews> reviews) {
        mReviews = reviews;
        notifyDataSetChanged();
    }
}
