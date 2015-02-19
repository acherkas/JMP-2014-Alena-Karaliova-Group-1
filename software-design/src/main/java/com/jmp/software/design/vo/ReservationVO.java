package com.jmp.software.design.vo;

import org.joda.time.LocalDateTime;

public class ReservationVO {

    private final long id;
    private final String movieTitle;
    private final LocalDateTime sessionTime;
    private final int seat;
    private final int price;
    private final ViewerVO viewerVO;

    public ReservationVO(String movieTitle, LocalDateTime sessionTime,
                         int seat, int price, ViewerVO viewerVO) {
        this(-1, movieTitle, sessionTime, seat, price, viewerVO);
    }

    public ReservationVO(long id, String movieTitle, LocalDateTime sessionTime,
                         int seat, int price, ViewerVO viewerVO) {
        this.id = id;
        this.movieTitle = movieTitle;
        this.sessionTime = sessionTime;
        this.seat = seat;
        this.price = price;
        this.viewerVO = viewerVO;
    }

    public long getId() {
        return id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public LocalDateTime getSessionTime() {
        return sessionTime;
    }

    public int getSeat() {
        return seat;
    }

    public int getPrice() {
        return price;
    }

    public ViewerVO getViewer() {
        return viewerVO;
    }
}
