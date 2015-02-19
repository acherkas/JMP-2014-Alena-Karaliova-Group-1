package com.jmp.software.design.model;

import org.joda.time.LocalTime;
import java.util.List;

public class MovieSession {

    private String movieTitle;
    private List<LocalTime> times;
    private int price;

    public MovieSession() {
    }

    public MovieSession(String movieTitle, List<LocalTime> times, int price) {
        this.movieTitle = movieTitle;
        this.times = times;
        this.price = price;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public List<LocalTime> getTimes() {
        return times;
    }

    public void setTimes(List<LocalTime> times) {
        this.times = times;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
