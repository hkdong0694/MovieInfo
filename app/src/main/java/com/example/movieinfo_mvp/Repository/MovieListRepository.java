package com.example.movieinfo_mvp.Repository;

import com.example.movieinfo_mvp.Network.MovieConst;
import com.example.movieinfo_mvp.Network.MovieInfoOpenApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieListRepository {

    private Retrofit retrofit;
    private MovieInfoOpenApiService movieInfoOpenApiService;

    public MovieListRepository(){
    }

    public MovieInfoOpenApiService initBuild(){
        retrofit = new Retrofit.Builder().baseUrl(MovieConst.base_url)
                .addConverterFactory(GsonConverterFactory.create()) //json 형태를 우리가 원하는 형태로 만들어주는 Gson라이브러리와 Retrofit2에 연결하는 코드
                .build();
        movieInfoOpenApiService = retrofit.create(MovieInfoOpenApiService.class);
        return movieInfoOpenApiService;
    }
}
