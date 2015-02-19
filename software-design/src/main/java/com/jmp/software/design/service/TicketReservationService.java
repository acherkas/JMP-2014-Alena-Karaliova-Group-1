package com.jmp.software.design.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jmp.software.design.converter.ReservationConverter;
import com.jmp.software.design.model.Reservation;
import com.jmp.software.design.vo.ReservationVO;
import org.springframework.stereotype.Service;

@Service
public class TicketReservationService {

    private List<Reservation> reservations = new ArrayList<>();
    private int counter;

    private ReservationConverter reservationConverter = new ReservationConverter();

    public ReservationVO reservationTicketForUser(ReservationVO resVO) {
        Reservation res = reservationConverter.convertToModel(resVO);
        if (isValidReservation(res)) {
            res.setId(++counter);
            reservations.add(res);
            return reservationConverter.convertToVO(res);
        }
        return null;
    }

    public boolean deleteReservation(long id) {
        Optional<Reservation> res = findReservationById(id);
        if (res.isPresent()) {
            reservations.remove(res.get());
            return true;
        }
        return false;
    }

    public Optional<Reservation> findReservationById(long id) {
        return reservations.stream()
                .filter(r -> r.getId() == id)
                .findFirst();
    }

    private boolean isValidReservation(Reservation res) {
        if (res == null) return false;
        if (res.getMovieTitle() == null) return false;
        if (res.getPrice() == 0) return false;
        if (res.getSeat() == 0) return false;
        if (res.getSessionTime() == null) return false;
        if (res.getViewer() == null) return false;
        return true;
    }
}
