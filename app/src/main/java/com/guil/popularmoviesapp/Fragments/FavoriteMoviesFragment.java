package com.guil.popularmoviesapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guil.popularmoviesapp.MainActivity;
import com.guil.popularmoviesapp.R;
import com.guil.popularmoviesapp.Utils.MiddleManClass;

import static com.guil.popularmoviesapp.Utils.Constants.NUM_COLUMNS;

public class FavoriteMoviesFragment extends Fragment {

    private RecyclerView mFavoriteRecyclerV;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.favorite_list_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFavoriteRecyclerV = view.findViewById(R.id.favorite_rv);
        context = MiddleManClass.getInstance().getContext();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mFavoriteRecyclerV.setAdapter(MainActivity.mFavoriteMoviesAdapter);
        mFavoriteRecyclerV.setLayoutManager(new GridLayoutManager(context, NUM_COLUMNS));
    }
}
