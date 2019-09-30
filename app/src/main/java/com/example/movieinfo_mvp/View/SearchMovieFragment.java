package com.example.movieinfo_mvp.View;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movieinfo_mvp.Contract.SearchMovieContract;
import com.example.movieinfo_mvp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchMovieFragment extends Fragment implements SearchMovieContract.View {


    public SearchMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void attach(Object presenter) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_movie, container, false);
    }

}
