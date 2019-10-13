package com.example.movieinfo_mvp.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.movieinfo_mvp.Adapter.SubDetailAdapter;
import com.example.movieinfo_mvp.Adapter.SubImageAdapter;
import com.example.movieinfo_mvp.Network.Model.KMDetail.MovieActor;
import com.example.movieinfo_mvp.Network.Model.KMDetail.MovieDirector;
import com.example.movieinfo_mvp.Network.Model.KMDetail.MovieQuery;
import com.example.movieinfo_mvp.Network.Model.KMDetail.MovieResult;
import com.example.movieinfo_mvp.Network.Model.NaverSearch.Item;
import com.example.movieinfo_mvp.Network.Model.RecyclerViewModel;
import com.example.movieinfo_mvp.Network.MovieConst;
import com.example.movieinfo_mvp.Network.MovieInfoOpenApiService;
import com.example.movieinfo_mvp.R;
import com.example.movieinfo_mvp.Repository.MovieDBRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity {

    private RecyclerViewModel recyclerViewModel;
    private RecyclerView sub_recycler;
    private RecyclerView sub_posterimage;

    private MovieInfoOpenApiService movieInfoOpenApiService;

    private SubDetailAdapter subDetailAdapter;
    private SubImageAdapter subImageAdapter;
    private LinearLayoutManager horziontallayout;

    private SharedPreferences sf;

    private TextView detail_title;
    private TextView detail_Engtitle;
    private TextView plot;
    private TextView sub_director;
    private TextView sub_actor;
    private ImageView imageuser;
    private ImageView sub_like;
    private ImageView background;
    private ImageView detailMovie_image;
    private Item item;

    private Gson gson = new GsonBuilder().create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        imageRandom();
        detail_title = findViewById(R.id.detail_title);
        detail_Engtitle = findViewById(R.id.detail_Engtitle);
        plot = findViewById(R.id.plot);
        detailMovie_image = findViewById(R.id.detailmovie_image);
        MovieDBRepository movieDBRepository = new MovieDBRepository();
        movieInfoOpenApiService = movieDBRepository.initBuild();
        String name = getIntent().getStringExtra("name");
        if(name.equals("search")) {
            item = (Item)getIntent().getSerializableExtra("movie");
            String namemodi;
            namemodi = item.getTitle().replace("</b>","").replace("<b>","");
            Log.e("Start",namemodi + "?");
            recyclerViewModel = new RecyclerViewModel(namemodi,item.getPubDate(),item.getSubtitle(),item.getImage());
        } else {
            recyclerViewModel = (RecyclerViewModel)getIntent().getSerializableExtra("movie");
        }
        Glide.with(getApplicationContext()).load(recyclerViewModel.getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).override(350,400).into(detailMovie_image);
        detailMovie_image.setClipToOutline(true);
        like_check(recyclerViewModel);
        detail_title.setText(recyclerViewModel.getMovieNm());
        detail_Engtitle.setText("( " + recyclerViewModel.getSubtitle() + " )");
        movieDetail(recyclerViewModel.getMovieNm(),recyclerViewModel.getPubDate());
    }

    public void movieDetail(String title, String openDt){
        movieInfoOpenApiService.getMovieDB(MovieConst.MovieDB_collection,title,openDt,MovieConst.MovieDB_service_key).enqueue(new Callback<MovieQuery>() {
            @Override
            public void onResponse(Call<MovieQuery> call, Response<MovieQuery> response) {
                if(response.isSuccessful()){
                    MovieQuery movieQuery = response.body();
                    MovieResult movieResult = movieQuery.getMovieData().get(0).getResult().get(0);
                    plot.setText(movieResult.getPlot());
                    sub_adapter(movieResult);
                    sub_emerge(movieResult);
                    String posters[] = movieResult.getStlls().split("\\|");
                    sub_poster(posters);
                } else {
                    Log.e("Start",response.message());
                }
            }
            @Override
            public void onFailure(Call<MovieQuery> call, Throwable t) {
                Log.e("Start",call.request().url().toString() + " detailActivity");
                Log.e("Start",t.getMessage());
            }
        });
    }

    //Poster ImageView에 붙여주는 함수
    public void sub_poster(String[] arr){
        sub_posterimage = findViewById(R.id.sub_posterimage);
        horziontallayout = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        sub_posterimage.setLayoutManager(horziontallayout);
        subImageAdapter = new SubImageAdapter(getApplicationContext(),arr);
        sub_posterimage.setAdapter(subImageAdapter);
    }

    //감독, 배우 textView에 넣어주는 함수
    public void sub_emerge(MovieResult movieResult){
        sub_director = findViewById(R.id.sub_director);
        sub_actor = findViewById(R.id.sub_actor);
        List<MovieDirector> movieDirectors = movieResult.getDirector();
        List<MovieActor> movieActors = movieResult.getActor();
        String director = "";
        String actor = "";
        for(MovieDirector movieDirector : movieDirectors) director += movieDirector.getDirectorNm() + " ";

        if(movieActors.size() > 5){
            for(int i=0;i<5;i++){
                actor += movieActors.get(i).getActorNm() + " ";
            }
        } else {
            for(MovieActor movieActor : movieActors) actor += movieActor.getActorNm() + " ";
        }
        sub_director.setText(director);
        sub_actor.setText(actor);
    }

    //제작년도, 장르, 타입, 제작, 키워드 textView에 넣어주는 함수
    public void sub_adapter(MovieResult movieResult) {
        sub_recycler = findViewById(R.id.sub_detail);
        subDetailAdapter = new SubDetailAdapter(this,movieResult);
        horziontallayout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        sub_recycler.setHasFixedSize(true);
        sub_recycler.setLayoutManager(horziontallayout);
        sub_recycler.setAdapter(subDetailAdapter);
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

    //하트를 표시한지 체크하는 함수
    public void like_check(RecyclerViewModel recyclerViewModel){
        sf = getSharedPreferences("Movielike", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        sub_like = findViewById(R.id.sub_like);
        String key = sf.getString("like",null);
        if(key != null) {
            ArrayList<RecyclerViewModel> datas = new ArrayList<>();
            Type listType = new TypeToken<ArrayList<RecyclerViewModel>>() {}.getType();
            datas = gson.fromJson(key, listType);
            boolean check = false;
            for(RecyclerViewModel arrayModel : datas){
                if(arrayModel.getMovieNm().equals(recyclerViewModel.getMovieNm())){
                    check = true;
                    break;
                }
            }
            if(!check) sub_like.setImageResource(R.drawable.ic_favorite_black_24dp);
            else sub_like.setImageResource(R.drawable.ic_favorite_blacks_24dp);
        } else sub_like.setImageResource(R.drawable.ic_favorite_black_24dp);
    }
}
