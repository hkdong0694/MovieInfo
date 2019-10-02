package com.example.movieinfo_mvp.Network;

import com.example.movieinfo_mvp.Network.Model.DBResult;
import com.example.movieinfo_mvp.Network.Model.MovieDetail;
import com.example.movieinfo_mvp.Network.Model.MoviedbModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface MovieDeatilService {

    @Headers({"X-Naver-Client-Id: " + MovieConst.CLIENT_ID,"X-Naver-Client-Secret: " + MovieConst.CLIENT_SECRET})
    @GET("movie.json")
    Call<MovieDetail> getMovies(@Query("query")String movieName, @Query("display") int display, @Query("yearfrom") int yearfrom, @Query("yearto") int yearto);

   /* @Headers({"X-Naver-Client-Id: " + MovieConst.CLIENT_ID,"X-Naver-Client-Secret: " + MovieConst.CLIENT_SECRET})
    @GET("movie.json")
    Call<MovieDetail> getSearch(@Query("query")String movieName, @Query("display") int display);*/

    @GET("openapi-data2/wisenut/search_api/search_json.jsp")
    Call<DBResult> getMovieDB(@Query("collection")String kmdb, @Query("ServiceKey") String key);

}
