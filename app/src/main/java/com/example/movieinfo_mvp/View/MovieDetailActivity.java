package com.example.movieinfo_mvp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.movieinfo_mvp.Network.Model.RecyclerViewModel;
import com.example.movieinfo_mvp.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class MovieDetailActivity extends AppCompatActivity {

    private RecyclerViewModel recyclerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        recyclerViewModel = (RecyclerViewModel)getIntent().getSerializableExtra("movieItem");
        String urlPath = recyclerViewModel.getLink();
        try{
            Document document = Jsoup.connect(urlPath).get();
            Log.e("Start",document.toString() + " 아");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
