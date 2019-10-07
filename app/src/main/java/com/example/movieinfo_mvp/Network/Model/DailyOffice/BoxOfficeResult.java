package com.example.movieinfo_mvp.Network.Model.DailyOffice;

import com.example.movieinfo_mvp.Network.Model.DailyOffice.DailyBoxOfficeList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BoxOfficeResult {

    /*
    boxofficeType	    문자열	    박스오피스 종류를 출력합니다.
    showRange	        문자열	    박스오피스 조회 일자를 출력합니다.
    DailyBoxOfficeLists
    */

    @Expose
    private String boxofficeType;
    @Expose
    private String showRange;
    @Expose
    @SerializedName("dailyBoxOfficeList")
    private List<DailyBoxOfficeList> DailyBoxOfficeLists = new ArrayList<>();

    public String getBoxofficeType() {
        return boxofficeType;
    }

    public void setBoxofficeType(String boxofficeType) {
        this.boxofficeType = boxofficeType;
    }

    public String getShowRange() {
        return showRange;
    }

    public void setShowRange(String showRange) {
        this.showRange = showRange;
    }

    public List<DailyBoxOfficeList> getDailyBoxOfficeLists() {
        return DailyBoxOfficeLists;
    }

    public void setDailyBoxOfficeLists(List<DailyBoxOfficeList> dailyBoxOfficeLists) {
        DailyBoxOfficeLists = dailyBoxOfficeLists;
    }
}
