package com.jmp.software.design.controller;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;




import com.jmp.software.design.config.AppConfig;
import com.jmp.software.design.model.Reservation;
import com.jmp.software.design.service.MovieSessionService;
import com.jmp.software.design.service.TicketReservationService;
import com.jmp.software.design.vo.MovieSessionVO;
import com.jmp.software.design.vo.ReservationVO;
import com.jmp.software.design.vo.ViewerVO;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class TicketReservationController {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MovieSessionService movieSessionService = (MovieSessionService)
                context.getBean("movieSessionService");
        TicketReservationService ticketReservationService = (TicketReservationService)
                context.getBean("ticketReservationService");

        Scanner scanner = new Scanner(System.in);

        // Registration
        System.out.println("Ticket reservation system");
        System.out.println("Enter your first name: ");
        String firstName = scanner.next();
        System.out.println("Enter your last name: ");
        String lastName = scanner.next();

        System.out.println("Input month: ");
        int month = scanner.nextInt();
        System.out.println("Input day: ");
        int day = scanner.nextInt();

        // Select movie session
        System.out.println("Available movie seasons: ");
        List<MovieSessionVO> mss =  movieSessionService.getMovieSessionByDate(
                LocalDate.of(2014, month, day));
        int counter = 1;
        for (MovieSessionVO ms : mss) {
            System.out.println(counter++ + " - " + ms.getMovieTitle());
        }
        System.out.println("Select movie session: ");
        int item = scanner.nextInt();

        // Select session time
        System.out.println("Available session time: ");
        MovieSessionVO ms =  mss.get(item - 1);
        counter = 1;
        for (LocalTime tm : ms.getTimes()) {
            System.out.println(counter++ + " - " + tm.toString());
        }
        System.out.println("Select session time: ");
        int timeItem = scanner.nextInt();

        // Select seat
        System.out.println("Select seat number (1-100): ");
        int seatItem = scanner.nextInt();

        // Create reservation
        ReservationVO resVO = new ReservationVO(ms.getMovieTitle(),
                LocalDateTime.of(LocalDate.of(2014, month, day), ms.getTimes().get(timeItem - 1)),
                seatItem, ms.getPrice(),
                new ViewerVO(firstName, lastName));
        resVO = ticketReservationService.reservationTicketForUser(resVO);
        if (resVO != null) {
            System.out.println("Reservation successfully created, your number is " + resVO.getId());
        } else {
            System.out.println("Error: Something gone wrong!");
            System.exit(0);
        }

        // Display reservation price by ID
        System.out.println("Enter reservation number: ");
        int resId = scanner.nextInt();
        Optional<Reservation> rsr =  ticketReservationService.findReservationById(resId);
        System.out.println("Reservation price is $" + rsr.get().getPrice());

        // Delete reservation by ID
        System.out.println("Enter reservation number for delete: ");
        int delResId = scanner.nextInt();
        ticketReservationService.deleteReservation(delResId);
    }
}
