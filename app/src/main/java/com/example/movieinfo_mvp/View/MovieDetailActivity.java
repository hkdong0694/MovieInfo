package com.example.movieinfo_mvp.View;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.movieinfo_mvp.Network.Model.KMDetail.MovieQuery;
import com.example.movieinfo_mvp.Network.Model.KMDetail.MovieResult;
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
    private ImageView posterimage;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        textView = findViewById(R.id.plot);
        posterimage = findViewById(R.id.posterimage);
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
                    MovieResult movieResult = movieQuery.getMovieData().get(0).getResult().get(0);
                    Log.e("Start",movieResult.getPosters() + " 포스터");
                    String posters[] = movieResult.getPosters().split("\\|");
                    Log.e("Start",posters.length + " 사이즈");
                    Glide.with(getApplicationContext()).load(posters[0]).override(300,300).diskCacheStrategy(DiskCacheStrategy.ALL).into(posterimage);
                    textView.setText(movieResult.getPlot());
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
