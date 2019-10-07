package com.example.movieinfo_mvp.Network.Model.NaverSearch;

import com.example.movieinfo_mvp.Network.Model.NaverSearch.Item;

import java.util.ArrayList;
import java.util.List;

public class MovieDetail {

    private int start;
    private int total;
    private int display;
    private String lastBuildDate;
    private List<Item> items = new ArrayList<>();

    public MovieDetail(int start, int total, int display, String lastBuildDate, List<Item> items) {
        this.start = start;
        this.total = total;
        this.display = display;
        this.lastBuildDate = lastBuildDate;
        this.items = items;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
