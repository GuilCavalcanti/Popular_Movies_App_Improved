package com.guil.popularmoviesapp.Adapters.MoviesDetailsRecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.guil.popularmoviesapp.Models.Trailers;
import com.guil.popularmoviesapp.R;
import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final String TAG = "MovieDetailsAdapter";
    private List<Trailers> mTrailers;
    private int size = 0;
    private boolean emptyList;

    public TrailerAdapter() {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailers_layout, parent, false);
        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String trailerName = "Trailer " + (position+1);
        if(emptyList) {
            ((TrailerViewHolder)holder).cardView.setVisibility(View.GONE);
        } else {
            ((TrailerViewHolder)holder).cardView.setVisibility(View.VISIBLE);
            if(mTrailers.get(position).getType().equals("Trailer")) {
                ((TrailerViewHolder)holder).trailerName.setText(trailerName);
            }
            ((TrailerViewHolder)holder).cardView.setOnClickListener(v -> {
                Intent intent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=" +
                                mTrailers.get(position)
                                        .getWatchKey()));
                v.getContext().startActivity(intent);
            });
        }
    }

    public void setTrailers(List<Trailers> trailers) {
        mTrailers = trailers;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        emptyList = false;
        size = 0;
        if(mTrailers != null) {
            for(Trailers trailer : mTrailers) {
                if(trailer.getType().equals("Trailer")) {
                    size++;
                }
            }
            if(size == 0) {
                emptyList = true;
                return 1;
            }
            return size;
        } else {
            return size;
        }
    }
}
