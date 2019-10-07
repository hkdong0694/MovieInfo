package com.example.movieinfo_mvp.Network.Model.KMDetail;

import java.util.List;

public class MovieData {

    private int TotalCount;
    private List<MovieResult> Result;

    public List<MovieResult> getResult() {
        return Result;
    }

    public void setResult(List<MovieResult> result) {
        Result = result;
    }

    public int getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(int totalCount) {
        TotalCount = totalCount;
    }
}
