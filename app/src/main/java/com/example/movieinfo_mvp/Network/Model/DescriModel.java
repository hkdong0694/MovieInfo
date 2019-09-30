package com.example.movieinfo_mvp.Network.Model;

public class DescriModel {

    /*
    query	        string (필수)	Y	-	검색을 원하는 질의. UTF-8 인코딩이다.
    display	        integer	N	기본값 10, 최대 100	검색 결과 출력 건수를 지정한다. 최대 100까지 가능하다.
    yearfrom	    integer(ex : 2000)	N	-	검색을 원하는 영화의 제작년도(최소)를 의미한다.
                    yearfrom은 yearto와 함께 사용되어야 한다.
    yearto	        integer(ex : 2008)	N	-	검색을 원하는 영화의 제작년도(최대)를 의미한다.
                    yearto는 yearfrom과 함께 사용되어야 한다.
    */

    private String movieName;
    private String startYear;
    private String endYear;

    public DescriModel(String movieName, String startYear, String endYear) {
        this.movieName = movieName;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
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

}
