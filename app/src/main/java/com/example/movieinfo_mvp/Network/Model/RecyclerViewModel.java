package com.example.movieinfo_mvp.Network.Model;


import java.io.Serializable;

//리싸이클러뷰에 붙일 모델
public class RecyclerViewModel implements Serializable {
    private static final long serialVersionUID = 1L;
/*
    rank	        문자열	        해당일자의 박스오피스 순위를 출력합니다.
    movieNm	        문자열	        영화명(국문)을 출력합니다.
    openDt	        문자열	        영화의 개봉일을 출력합니다.
    pubDate	        문자열	        영화의 개봉년도를 출력합니다.
    audiAcc	        문자열	        누적관객수를 출력합니다.
    link	        string	        검색 결과 영화의 하이퍼텍스트 link를 나타낸다.
    image	        string	        검색 결과 영화의 썸네일 이미지의 URL이다. 이미지가 있는 경우만 나타난다.
    director	    string	        검색 결과 영화의 감독이다.
    actor	        string	        검색 결과 영화의 출연 배우이다.
    userRating	    integer	        검색 결과 영화에 대한 유저들의 평점이다.
*/

    private String rank;
    private String movieNm;
    private String openDt;
    private String audiAcc;
    private String link;
    private String image;
    private String director;
    private String actor;
    private String userRating;
    private String startYear;
    private String endYear;
    private String pubDate;
    private Boolean check;

    public RecyclerViewModel(String rank,String movieNm, String openDt, String audiAcc,String startYear, String endYear) {
        this.rank = rank;
        this.movieNm = movieNm;
        this.openDt = openDt;
        this.audiAcc = audiAcc;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
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

    public String getAudiAcc() {
        return audiAcc;
    }

    public void setAudiAcc(String audiAcc) {
        this.audiAcc = audiAcc;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }
}
