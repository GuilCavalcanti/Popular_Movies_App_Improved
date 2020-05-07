package com.guil.popularmoviesapp.Adapters.FragmentAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import java.util.ArrayList;
import java.util.List;

public class FragmentAdapter extends FragmentStateAdapter {

    private List<Fragment> mFragments;

    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        mFragments = new ArrayList<>();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        return mFragments.get(position);
    }

    @Override
    public int getItemCount() {
        return mFragments.size();
    }

    public void addFragments(Fragment frag) {
        mFragments.add(frag);
    }
}
