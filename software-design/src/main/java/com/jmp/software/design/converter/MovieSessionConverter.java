package com.jmp.software.design.converter;

import com.jmp.software.design.model.MovieSession;
import com.jmp.software.design.vo.MovieSessionVO;

public class MovieSessionConverter {

    public MovieSession convertToModel(MovieSessionVO movieSessionVO) {
        MovieSession movieSession = new MovieSession();
        movieSession.setMovieTitle(movieSessionVO.getMovieTitle());
        movieSession.setTimes(movieSessionVO.getTimes());
        movieSession.setPrice(movieSessionVO.getPrice());
        return movieSession;
    }

    public MovieSessionVO convertToVO(MovieSession movieSession) {
        return new MovieSessionVO(movieSession.getMovieTitle(),
                movieSession.getTimes(), movieSession.getPrice());
    }
}
