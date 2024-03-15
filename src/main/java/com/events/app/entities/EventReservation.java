//package com.proiect_final.proiect_final.entities;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.persistence.*;
//
//@Entity
//public class EventReservation {
//
//    @Id
//    @GeneratedValue
//    private Long id;
//
//    @ManyToOne
//    @JsonBackReference("reservation-eventreservations")
//    @JoinColumn(name = "reservation_id")
//    private Reservation reservation;
//
//    @ManyToOne
//    @JsonBackReference("event-eventreservations")
//    @JoinColumn(name = "event_id")
//    private Event event;
//
//    public EventReservation() {
//
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Reservation getReservation() {
//        return reservation;
//    }
//
//    public void setReservation(Reservation reservation) {
//        this.reservation = reservation;
//    }
//
//    public Event getEvent() {
//        return event;
//    }
//
//    public void setEvent(Event event) {
//        this.event = event;
//    }
//}
