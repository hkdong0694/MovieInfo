package com.example.movieinfo_mvp.Network.Model;

import com.google.gson.annotations.Expose;

public class DailyBoxOfficeList {
   /*
    rnum	        문자열	        순번을 출력합니다.
    rank	        문자열	        해당일자의 박스오피스 순위를 출력합니다.
    rankInten	    문자열	        전일대비 순위의 증감분을 출력합니다.
    rankOldAndNew	문자열	        랭킹에 신규진입여부를 출력합니다. “OLD” : 기존 , “NEW” : 신규
    movieCd	        문자열	        영화의 대표코드를 출력합니다.
    movieNm	        문자열	        영화명(국문)을 출력합니다.
    openDt	        문자열	        영화의 개봉일을 출력합니다.
    salesAmt	    문자열	        해당일의 매출액을 출력합니다.
    salesShare	    문자열	        해당일자 상영작의 매출총액 대비 해당 영화의 매출비율을 출력합니다.
    salesInten	    문자열	        전일 대비 매출액 증감분을 출력합니다.
    salesChange	    문자열	        전일 대비 매출액 증감 비율을 출력합니다.
    salesAcc	    문자열	        누적매출액을 출력합니다.
    audiCnt	        문자열	        해당일의 관객수를 출력합니다.
    audiInten	    문자열	        전일 대비 관객수 증감분을 출력합니다.
    audiChange	    문자열	        전일 대비 관객수 증감 비율을 출력합니다.
    audiAcc	        문자열	        누적관객수를 출력합니다.
    scrnCnt	        문자열	        해당일자에 상영한 스크린수를 출력합니다.
    showCnt	        문자열	        해당일자에 상영된 횟수를 출력합니다.
    */
    @Expose
    private String rnum;
    @Expose
    private String rank;
    @Expose
    private String rankInten;
    @Expose
    private String rankOldAndNew;
    @Expose
    private String movieCd;
    @Expose
    private String movieNm;
    @Expose
    private String openDt;
    @Expose
    private String salesAmt;
    @Expose
    private String salesInten;
    @Expose
    private String salesChange;
    @Expose
    private String salesAcc;
    @Expose
    private String audiCnt;
    @Expose
    private String audiInten;
    @Expose
    private String audiChange;
    @Expose
    private String audiAcc;
    @Expose
    private String scrnCnt;
    @Expose
    private String showCnt;

    public DailyBoxOfficeList(String rnum, String rank, String rankInten, String rankOldAndNew, String movieCd, String movieNm, String openDt, String salesAmt, String salesInten, String salesChange, String salesAcc, String audiCnt, String audiInten, String audiChange, String audiAcc, String scrnCnt, String showCnt) {
        this.rnum = rnum;
        this.rank = rank;
        this.rankInten = rankInten;
        this.rankOldAndNew = rankOldAndNew;
        this.movieCd = movieCd;
        this.movieNm = movieNm;
        this.openDt = openDt;
        this.salesAmt = salesAmt;
        this.salesInten = salesInten;
        this.salesChange = salesChange;
        this.salesAcc = salesAcc;
        this.audiCnt = audiCnt;
        this.audiInten = audiInten;
        this.audiChange = audiChange;
        this.audiAcc = audiAcc;
        this.scrnCnt = scrnCnt;
        this.showCnt = showCnt;
    }

    public String getRnum() {
        return rnum;
    }

    public void setRnum(String rnum) {
        this.rnum = rnum;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRankInten() {
        return rankInten;
    }

    public void setRankInten(String rankInten) {
        this.rankInten = rankInten;
    }

    public String getRankOldAndNew() {
        return rankOldAndNew;
    }

    public void setRankOldAndNew(String rankOldAndNew) {
        this.rankOldAndNew = rankOldAndNew;
    }

    public String getMovieCd() {
        return movieCd;
    }

    public void setMovieCd(String movieCd) {
        this.movieCd = movieCd;
    }

    public String getMovieNm() {
        return movieNm;
    }

    public void setMovieNm(String movieNm) {
        this.movieNm = movieNm;
    }

    public String getOpenDt() {
        return openDt;
    }

    public void setOpenDt(String openDt) {
        this.openDt = openDt;
    }

    public String getSalesAmt() {
        return salesAmt;
    }

    public void setSalesAmt(String salesAmt) {
        this.salesAmt = salesAmt;
    }

    public String getSalesInten() {
        return salesInten;
    }

    public void setSalesInten(String salesInten) {
        this.salesInten = salesInten;
    }

    public String getSalesChange() {
        return salesChange;
    }

    public void setSalesChange(String salesChange) {
        this.salesChange = salesChange;
    }

    public String getSalesAcc() {
        return salesAcc;
    }

    public void setSalesAcc(String salesAcc) {
        this.salesAcc = salesAcc;
    }

    public String getAudiCnt() {
        return audiCnt;
    }

    public void setAudiCnt(String audiCnt) {
        this.audiCnt = audiCnt;
    }

    public String getAudiInten() {
        return audiInten;
    }

    public void setAudiInten(String audiInten) {
        this.audiInten = audiInten;
    }

    public String getAudiChange() {
        return audiChange;
    }

    public void setAudiChange(String audiChange) {
        this.audiChange = audiChange;
    }

    public String getAudiAcc() {
        return audiAcc;
    }

    public void setAudiAcc(String audiAcc) {
        this.audiAcc = audiAcc;
    }

    public String getScrnCnt() {
        return scrnCnt;
    }

    public void setScrnCnt(String scrnCnt) {
        this.scrnCnt = scrnCnt;
    }

    public String getShowCnt() {
        return showCnt;
    }

    public void setShowCnt(String showCnt) {
        this.showCnt = showCnt;
    }
}
