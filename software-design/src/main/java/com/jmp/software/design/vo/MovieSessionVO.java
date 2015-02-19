package com.jmp.software.design.vo;

import org.joda.time.LocalTime;
import java.util.List;

public class MovieSessionVO {

    private final String movieTitle;
    private final List<LocalTime> times;
    private final int price;

    public MovieSessionVO(String movieTitle, List<LocalTime> times, int price) {
        this.movieTitle = movieTitle;
        this.times = times;
        this.price = price;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public List<LocalTime> getTimes() {
        return times;
    }

    public int getPrice() {
        return price;
    }
}
