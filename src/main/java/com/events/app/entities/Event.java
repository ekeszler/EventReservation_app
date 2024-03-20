package com.events.app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;


    @ManyToOne
    @JsonBackReference("user-events")
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private LocalDateTime start;

    @Column
    private LocalDateTime end;


    @OneToMany(mappedBy = "event", cascade = {CascadeType.MERGE, CascadeType.MERGE})
    @JsonManagedReference("packages")
    private List<Package> packages;


    @Column
    private String galeryLink;

    @Column
    private String review;

    public Event() {
    }

    public Event(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Package> getCustomerPackages() {
        return packages;
    }

    public void setCustomerPackages(List<Package> Packages) {
        this.packages = Packages;
    }

    public String getGaleryLink() {
        return galeryLink;
    }

    public void setGaleryLink(String galeryLink) {
        this.galeryLink = galeryLink;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
