package com.proiect_final.proiect_final.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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
    @JsonManagedReference("event-customerPackages")
    private List<CustomerPackage> customerPackages;


    @Column
    private String galeryLink;

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



    public List<CustomerPackage> getCustomerPackages() {
        return customerPackages;
    }

    public void setCustomerPackages(List<CustomerPackage> customerPackages) {
        this.customerPackages = customerPackages;
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
}
