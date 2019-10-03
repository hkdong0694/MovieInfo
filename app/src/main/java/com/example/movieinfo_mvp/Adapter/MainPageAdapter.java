package com.example.movieinfo_mvp.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.movieinfo_mvp.View.DailyMovieFragment;
import com.example.movieinfo_mvp.View.SearchMovieFragment;
import com.example.movieinfo_mvp.View.TopMovieFragment;

public class MainPageAdapter extends FragmentPagerAdapter {

    private int tabCount;
    private DailyMovieFragment dailyMovieFragment;
    private SearchMovieFragment searchMovieFragment;
    private TopMovieFragment topMovieFragment;

    public MainPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.tabCount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                dailyMovieFragment = new DailyMovieFragment();
                return dailyMovieFragment;
            case 1:
                searchMovieFragment = new SearchMovieFragment();
                return searchMovieFragment;
            case 2:
                topMovieFragment = new TopMovieFragment();
                return topMovieFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
