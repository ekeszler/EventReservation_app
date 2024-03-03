package com.proiect_final.proiect_final.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "reservation", cascade = {CascadeType.MERGE, CascadeType.MERGE})
    @JsonManagedReference("reservation-eventreservations")
    private List<EventReservation> eventReservations;

    @ManyToOne
    @JsonBackReference("user-reservations")
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private LocalDateTime eventDate;

    public Reservation() {
    }

    public Reservation(LocalDateTime eventDate) {
        eventReservations = new ArrayList<>();
        this.eventDate = eventDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<EventReservation> getEventReservations() {
        return eventReservations;
    }

    public void setEventReservations(List<EventReservation> eventReservations) {
        this.eventReservations = eventReservations;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }
}
