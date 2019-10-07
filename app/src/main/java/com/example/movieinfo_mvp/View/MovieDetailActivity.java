package com.example.movieinfo_mvp.View;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.movieinfo_mvp.Network.Model.KMDetail.MovieQuery;
import com.example.movieinfo_mvp.Network.Model.NaverSearch.Item;
import com.example.movieinfo_mvp.Network.Model.RecyclerViewModel;
import com.example.movieinfo_mvp.Network.MovieConst;
import com.example.movieinfo_mvp.Network.MovieInfoOpenApiService;
import com.example.movieinfo_mvp.R;
import com.example.movieinfo_mvp.Repository.MovieDBRepository;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity {

    private RecyclerViewModel recyclerViewModel;
    private MovieInfoOpenApiService movieInfoOpenApiService;
    private TextView textView;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        textView = findViewById(R.id.plot);
        MovieDBRepository movieDBRepository = new MovieDBRepository();
        movieInfoOpenApiService = movieDBRepository.initBuild();
        String name = getIntent().getStringExtra("name");
        if(name.equals("search")){
            item = (Item)getIntent().getSerializableExtra("movie");
            movieDetail(item.getTitle(),item.getPubDate());
        } else{
            recyclerViewModel = (RecyclerViewModel)getIntent().getSerializableExtra("movie");
            movieDetail(recyclerViewModel.getMovieNm(),recyclerViewModel.getPubDate());
        }
    }


    public void movieDetail(String title, String openDt){
        movieInfoOpenApiService.getMovieDB(MovieConst.MovieDB_collection,title,openDt,MovieConst.MovieDB_service_key).enqueue(new Callback<MovieQuery>() {
            @Override
            public void onResponse(Call<MovieQuery> call, Response<MovieQuery> response) {
                if(response.isSuccessful()){
                    MovieQuery movieQuery = response.body();
                    Log.e("Start",movieQuery.getMovieData().get(0).getResult().get(0).getPlot() + " 개");
                    textView.setText(movieQuery.getMovieData().get(0).getResult().get(0).getPlot());
                } else {

                }
            }

            @Override
            public void onFailure(Call<MovieQuery> call, Throwable t) {
                Log.e("Start",call.request().url().toString() + " detailActivity");
            }
        });
    }
}
