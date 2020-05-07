package com.guil.popularmoviesapp.Adapters.MoviesDetailsRecyclerView;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.guil.popularmoviesapp.R;

public class TrailerViewHolder extends RecyclerView.ViewHolder{

    public TextView trailerName;
    public View cardView;

    public TrailerViewHolder(@NonNull View itemView) {
        super(itemView);
        trailerName = itemView.findViewById(R.id.trailer_tv);
        cardView = itemView.findViewById(R.id.cardViewMiddle);
    }
}
