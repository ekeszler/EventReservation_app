package com.proiect_final.proiect_final.services;

import com.proiect_final.proiect_final.dtos.ReservationRequestDTO;
import com.proiect_final.proiect_final.entities.Event;
import com.proiect_final.proiect_final.entities.EventReservation;
import com.proiect_final.proiect_final.entities.Reservation;
import com.proiect_final.proiect_final.entities.User;
import com.proiect_final.proiect_final.exceptions.ResourceNotFoundException;
import com.proiect_final.proiect_final.repositories.EventRepository;
import com.proiect_final.proiect_final.repositories.ReservationRepository;
import com.proiect_final.proiect_final.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private UserRepository userRepository;
    private ReservationRepository reservationRepository;

    private EventRepository eventRepository;

    @Autowired
    public ReservationService(UserRepository userRepository, ReservationRepository reservationRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
        this.eventRepository = eventRepository;
    }
    @Transactional
    public Reservation createReservation(ReservationRequestDTO reservationRequestDTO){
        User user = userRepository.findById(reservationRequestDTO.getUserId()).orElseThrow(() ->new ResourceNotFoundException("user not found"));
        List<Event> scheduledEvent = eventRepository.findAllById(reservationRequestDTO.getEventIds());

        for(Event event: scheduledEvent){
            if(existsEventBetween(event, reservationRequestDTO.getEventDate())){
                throw new ResourceNotFoundException("this date is already reserved");
            }
        }
        Reservation reservation = new Reservation();
        reservation.setEventDate(reservationRequestDTO.getEventDate());

        List<EventReservation> eventReservations = scheduledEvent.stream()
                .map(event -> mapFromEventToEventReservation(event, reservation))
                .collect(Collectors.toList());
        reservation.setEventReservations(eventReservations);
        reservation.setUser(user);

        return reservationRepository.save(reservation);
    }


    public EventReservation mapFromEventToEventReservation(Event event, Reservation reservation){
        EventReservation eventReservation = new EventReservation();
        eventReservation.setReservation(reservation);
        eventReservation.setEvent(event);
        reservation.getEventReservations().add(eventReservation);
        return eventReservation;
    }

    public boolean existsEventBetween (Event event, LocalDateTime start, LocalDateTime end){
       // return et.getDate().stream()
            //    .map(eventReservation -> eventReservation.getReservation())
            //    .noneMatch(reservation -> reservation.getEventDate().isEqual(eventDate));ven


            return event.getCheckIn().isBefore(start) && event.getCheckOut().isAfter(end)
                    || reservation.getCheckOut().isAfter(checkIn) && reservation.getCheckIn().isBefore(checkOut)
                    || reservation.getCheckIn().isEqual(checkIn) || reservation.getCheckOut().isEqual(checkOut);
        }
    }
}
