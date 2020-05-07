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

public class PopularMoviesFragment extends Fragment {

    private RecyclerView mPopularRecyclerV;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.popular_list_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPopularRecyclerV = view.findViewById(R.id.popular_rv);
        context = MiddleManClass.getInstance().getContext();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mPopularRecyclerV.setAdapter(MainActivity.mPopularMoviesAdapter);
        mPopularRecyclerV.setLayoutManager(new GridLayoutManager(context, NUM_COLUMNS));
    }
}
