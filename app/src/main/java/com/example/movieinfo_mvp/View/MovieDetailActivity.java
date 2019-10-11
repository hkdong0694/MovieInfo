package com.example.movieinfo_mvp.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.movieinfo_mvp.Adapter.ImageViewPager;
import com.example.movieinfo_mvp.Network.Model.KMDetail.MovieActor;
import com.example.movieinfo_mvp.Network.Model.KMDetail.MovieQuery;
import com.example.movieinfo_mvp.Network.Model.KMDetail.MovieRating;
import com.example.movieinfo_mvp.Network.Model.KMDetail.MovieResult;
import com.example.movieinfo_mvp.Network.Model.KMDetail.MovieStaff;
import com.example.movieinfo_mvp.Network.Model.NaverSearch.Item;
import com.example.movieinfo_mvp.Network.Model.RecyclerViewModel;
import com.example.movieinfo_mvp.Network.MovieConst;
import com.example.movieinfo_mvp.Network.MovieInfoOpenApiService;
import com.example.movieinfo_mvp.R;
import com.example.movieinfo_mvp.Repository.MovieDBRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity {

    private RecyclerViewModel recyclerViewModel;
    private MovieInfoOpenApiService movieInfoOpenApiService;
    private ImageViewPager imageViewPager;
    private ImageView imageCenter;
    private TextView detail_title;
    private TextView detail_Engtitle;
    private TextView plot;
    //private CircleIndicator indicator;
    //private ViewPager viewPager;
    private ImageView imageuser;
    private ImageView background;
    private ImageView detailMovie_image;
    private MovieRating movieRating;
    private List<String> imageposterList;
    private ActionBar actionBar;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        actionBar = getSupportActionBar();
        actionBar.hide();
        //이미지 Background 랜덤으로 넣어주는 함수
        imageRandom();
        Log.e("Start"," MovieDetail");
        //viewPager = findViewById(R.id.viewpager);
        //indicator = findViewById(R.id.indicator);
        imageCenter = findViewById(R.id.imageCenter);
        detail_title = findViewById(R.id.detail_title);
        detail_Engtitle = findViewById(R.id.detail_Engtitle);
        plot = findViewById(R.id.plot);
        detailMovie_image = findViewById(R.id.detailmovie_image);
        MovieDBRepository movieDBRepository = new MovieDBRepository();
        imageposterList = new ArrayList<>();
        movieInfoOpenApiService = movieDBRepository.initBuild();
        String name = getIntent().getStringExtra("name");
        if(name.equals("search")) {
            item = (Item)getIntent().getSerializableExtra("movie");
            movieDetail(item.getTitle(),item.getPubDate());
        } else {
            recyclerViewModel = (RecyclerViewModel)getIntent().getSerializableExtra("movie");
            Glide.with(getApplicationContext()).load(recyclerViewModel.getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).override(350,400).into(detailMovie_image);
            detailMovie_image.setClipToOutline(true);
            detail_title.setText(recyclerViewModel.getMovieNm());
            detail_Engtitle.setText("( " + recyclerViewModel.getSubtitle() + " )");
            //Glide.with(getApplicationContext()).load(recyclerViewModel.getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).override(250,250).into(imageCenter);
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
                    plot.setText(movieResult.getPlot());
                    String posters[] = movieResult.getStlls().split("\\|");
                    MovieActor movieActor = movieResult.getActor().get(0);
                    Log.e("Start",movieActor.getActorNm() + " ??");
                    for(String a : posters) imageposterList.add(a);
                    imageposterList.remove(0);
                    imageViewPager = new ImageViewPager(getApplicationContext(),imageposterList);
                    //viewPager.setAdapter(imageViewPager);
                    //indicator.setViewPager(viewPager);
                } else {

                }
            }

            @Override
            public void onFailure(Call<MovieQuery> call, Throwable t) {
                Log.e("Start",call.request().url().toString() + " detailActivity");
                Log.e("Start",t.getMessage());
            }
        });
    }

    // 관람가에 따른 imageview 붙여주는 함수
    public void visiting(String user){
        imageuser = findViewById(R.id.user);
        //String user = movieRating.getRatingGrade();
        Log.e("Start",user + " ok");
        Drawable drawable;
        if(user.equals("전체관람가"))        drawable = getDrawable(R.drawable.all);
        else if(user.equals("12세관람가"))   drawable = getDrawable(R.drawable.twelve);
        else if(user.equals("15세관람가"))   drawable = getDrawable(R.drawable.fifteen);
        else                                 drawable = getDrawable(R.drawable.eighteen);
        Glide.with(getApplicationContext()).load(drawable)
                .diskCacheStrategy(DiskCacheStrategy.ALL).override(100,100).into(imageuser);
    }

    //맨 위 배경화면 background random으로 지정해주는 함수
    public void imageRandom(){
        background = findViewById(R.id.imagebackground);
        int random = (int)(Math.random()*6) + 1;
        switch (random){
            case 1: background.setBackground(getDrawable(R.drawable.movie_1)); break;
            case 2: background.setBackground(getDrawable(R.drawable.movie_2)); break;
            case 3: background.setBackground(getDrawable(R.drawable.movie_3)); break;
            case 4: background.setBackground(getDrawable(R.drawable.movie_4)); break;
            case 5: background.setBackground(getDrawable(R.drawable.movie_5)); break;
            case 6: background.setBackground(getDrawable(R.drawable.movie_6)); break;
        }
    }
}
