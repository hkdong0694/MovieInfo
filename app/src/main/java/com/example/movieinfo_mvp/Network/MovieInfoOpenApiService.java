package com.example.movieinfo_mvp.Network;

import com.example.movieinfo_mvp.Network.Model.KMDetail.MovieData;
import com.example.movieinfo_mvp.Network.Model.KMDetail.MovieQuery;
import com.example.movieinfo_mvp.Network.Model.NaverSearch.MovieDetail;
import com.example.movieinfo_mvp.Network.Model.DailyOffice.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface MovieInfoOpenApiService {

    @GET("/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json")
    Call<Result> getBoxOffice(@Query("key") String key, @Query("targetDt") String targetDt);

    @Headers({"X-Naver-Client-Id: " + MovieConst.CLIENT_ID,"X-Naver-Client-Secret: " + MovieConst.CLIENT_SECRET})
    @GET("movie.json")
    Call<MovieDetail> getMovies(@Query("query")String movieName, @Query("display") int display, @Query("yearfrom") int yearfrom, @Query("yearto") int yearto);

    @GET("openapi-data2/wisenut/search_api/search_json.jsp")
    Call<MovieQuery> getMovieDB(@Query("collection")String kmdb, @Query("title")String title, @Query("createDts")String openDt, @Query("ServiceKey") String key);

}
