package com.example.movieinfo_mvp.View;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movieinfo_mvp.Contract.TopMovieContract;
import com.example.movieinfo_mvp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopMovieFragment extends Fragment implements TopMovieContract.View {


    public TopMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void attach(Object presenter) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top_movie, container, false);
    }

}
