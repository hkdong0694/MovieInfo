package com.example.movieinfo_mvp.Network.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DBResult {

    @SerializedName("Row Value")
    private List<MoviedbModel> moviedbModels;

    public List<MoviedbModel> getMoviedbModels() {
        return moviedbModels;
    }

    public void setMoviedbModels(List<MoviedbModel> moviedbModels) {
        this.moviedbModels = moviedbModels;
    }

}
