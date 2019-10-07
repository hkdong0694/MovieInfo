package com.example.movieinfo_mvp.View;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movieinfo_mvp.Contract.TopMovieContract;
import com.example.movieinfo_mvp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopMovieFragment extends Fragment implements TopMovieContract.View {

    private SharedPreferences sf;

    public TopMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void attach(Object presenter) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        sf = getContext().getSharedPreferences("Movielike", Context.MODE_PRIVATE);

        String key = sf.getString("like",null);

        if(key == null) {
            Log.e("Start", "암것도 없음");
        } else{
            Log.e("Start",key + " pko");
        }

        return inflater.inflate(R.layout.fragment_top_movie, container, false);



    }

}
