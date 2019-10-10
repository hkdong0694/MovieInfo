package com.example.movieinfo_mvp.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.movieinfo_mvp.Adapter.ImageViewPager;
import com.example.movieinfo_mvp.Network.Model.KMDetail.MovieQuery;
import com.example.movieinfo_mvp.Network.Model.KMDetail.MovieResult;
import com.example.movieinfo_mvp.Network.Model.NaverSearch.Item;
import com.example.movieinfo_mvp.Network.Model.RecyclerViewModel;
import com.example.movieinfo_mvp.Network.MovieConst;
import com.example.movieinfo_mvp.Network.MovieInfoOpenApiService;
import com.example.movieinfo_mvp.R;
import com.example.movieinfo_mvp.Repository.MovieDBRepository;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity {

    private RecyclerViewModel recyclerViewModel;
    private MovieInfoOpenApiService movieInfoOpenApiService;
    private ImageViewPager imageViewPager;
    private ImageView imageCenter;
    private ViewPager viewPager;
    private CircleIndicator indicator;
    private List<String> imageposterList;
    private ActionBar actionBar;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        viewPager = findViewById(R.id.viewpager);
        indicator = findViewById(R.id.indicator) ;
        imageCenter = findViewById(R.id.imageCenter);
        actionBar = getSupportActionBar();
        actionBar.hide();
        MovieDBRepository movieDBRepository = new MovieDBRepository();
        imageposterList = new ArrayList<>();
        movieInfoOpenApiService = movieDBRepository.initBuild();
        String name = getIntent().getStringExtra("name");
        if(name.equals("search")) {
            item = (Item)getIntent().getSerializableExtra("movie");
            movieDetail(item.getTitle(),item.getPubDate());
        } else {
            recyclerViewModel = (RecyclerViewModel)getIntent().getSerializableExtra("movie");
            Glide.with(getApplicationContext()).load(recyclerViewModel.getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).override(250,250).into(imageCenter);
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
                    for(String a : posters) imageposterList.add(a);
                    imageposterList.remove(0);
                    imageViewPager = new ImageViewPager(getApplicationContext(),imageposterList);
                    viewPager.setAdapter(imageViewPager);
                    indicator.setViewPager(viewPager);
                    Log.e("Start",posters.length + " 사이즈");
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
