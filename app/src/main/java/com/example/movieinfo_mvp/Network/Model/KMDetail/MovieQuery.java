package com.example.movieinfo_mvp.Network.Model.KMDetail;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieQuery {

    @SerializedName("TotalCount")
    String totalcount;
    @SerializedName("Data")
    List<MovieData> movieData;

    public String getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(String totalcount) {
        this.totalcount = totalcount;
    }

    public List<MovieData> getMovieData() {
        return movieData;
    }

    public void setMovieData(List<MovieData> movieData) {
        this.movieData = movieData;
    }
}
