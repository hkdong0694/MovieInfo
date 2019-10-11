package com.example.movieinfo_mvp.Network.Model.KMDetail;

import com.google.gson.annotations.SerializedName;

public class MovieRating {

    private String ratingMain;
    private String ratingDate;
    private String ratingNo;
    private String ratingGrade;

    public String getRatingMain() {
        return ratingMain;
    }

    public void setRatingMain(String ratingMain) {
        this.ratingMain = ratingMain;
    }

    public String getRatingDate() {
        return ratingDate;
    }

    public void setRatingDate(String ratingDate) {
        this.ratingDate = ratingDate;
    }

    public String getRatingNo() {
        return ratingNo;
    }

    public void setRatingNo(String ratingNo) {
        this.ratingNo = ratingNo;
    }

    public String getRatingGrade() {
        return ratingGrade;
    }

    public void setRatingGrade(String ratingGrade) {
        this.ratingGrade = ratingGrade;
    }

}
