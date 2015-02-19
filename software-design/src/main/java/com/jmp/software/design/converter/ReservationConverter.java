package com.jmp.software.design.converter;

import com.jmp.software.design.model.Reservation;
import com.jmp.software.design.vo.ReservationVO;

public class ReservationConverter {

    private ViewerConverter viewerConverter = new ViewerConverter();

    public Reservation convertToModel(ReservationVO resVO) {
        Reservation res = new Reservation();
        res.setMovieTitle(resVO.getMovieTitle());
        res.setSessionTime(resVO.getSessionTime());
        res.setSeat(resVO.getSeat());
        res.setPrice(resVO.getPrice());
        res.setViewer(viewerConverter.convertToModel(resVO.getViewer()));
        return res;
    }

    public ReservationVO convertToVO(Reservation res) {
        ReservationVO resVO = new ReservationVO(res.getId(),
                res.getMovieTitle(), res.getSessionTime(),
                res.getSeat(), res.getPrice(),
                viewerConverter.convertToVO(res.getViewer()));
        return resVO;
    }
}
