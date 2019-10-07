package com.example.movieinfo_mvp.Network.Model.KMDetail;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResult {

    @SerializedName("DOCID")
    private String docid;
    private String movieId;
    private String movieSeq;
    private String title;
    private String titleEng;
    private String titleOrg;
    private String titleEtc;
    private String plot;
    private String prodYear;
    @SerializedName("director")
    private List<MovieDirector> director;
    @SerializedName("actor")
    private List<MovieActor> actor;
    private String nation;
    private String company;
    @SerializedName("runtime")
    private String runTime;
    //private String rating;
    private String genre;
    private String kmdbUrl;
    private String type;
    private String use;
    private String episodes;
    private String ratedYn;
    private String repRatDate;
    //R옆에 i인지 l 인지 구분 불가
    private String repRlsDate;
    private String keywords;
    @SerializedName("posters")
    private String posters;
    private String stlls;
    private List<MovieStaff> staff;

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieSeq() {
        return movieSeq;
    }

    public void setMovieSeq(String movieSeq) {
        this.movieSeq = movieSeq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleEng() {
        return titleEng;
    }

    public void setTitleEng(String titleEng) {
        this.titleEng = titleEng;
    }

    public String getTitleOrg() {
        return titleOrg;
    }

    public void setTitleOrg(String titleOrg) {
        this.titleOrg = titleOrg;
    }

    public String getTitleEtc() {
        return titleEtc;
    }

    public void setTitleEtc(String titleEtc) {
        this.titleEtc = titleEtc;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

        public String getProdYear() {
        return prodYear;
    }

    public void setProdYear(String prodYear) {
        this.prodYear = prodYear;
    }

    public List<MovieDirector> getDirector() {
        return director;
    }

    public void setDirector(List<MovieDirector> director) {
        this.director = director;
    }

    public List<MovieActor> getActor() {
        return actor;
    }

    public void setActor(List<MovieActor> actor) {
        this.actor = actor;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getKmdbUrl() {
        return kmdbUrl;
    }

    public void setKmdbUrl(String kmdbUrl) {
        this.kmdbUrl = kmdbUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getEpisodes() {
        return episodes;
    }

    public void setEpisodes(String episodes) {
        this.episodes = episodes;
    }

    public String getRatedYn() {
        return ratedYn;
    }

    public void setRatedYn(String ratedYn) {
        this.ratedYn = ratedYn;
    }

    public String getRepRatDate() {
        return repRatDate;
    }

    public void setRepRatDate(String repRatDate) {
        this.repRatDate = repRatDate;
    }

    public String getRepRlsDate() {
        return repRlsDate;
    }

    public void setRepRlsDate(String repRlsDate) {
        this.repRlsDate = repRlsDate;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getPosters() {
        return posters;
    }

    public void setPosters(String posters) {
        this.posters = posters;
    }

    public String getStlls() {
        return stlls;
    }

    public void setStlls(String stlls) {
        this.stlls = stlls;
    }

    public List<MovieStaff> getStaff() {
        return staff;
    }

    public void setStaff(List<MovieStaff> staff) {
        this.staff = staff;
    }

}
