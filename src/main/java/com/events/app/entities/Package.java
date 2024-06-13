package com.events.app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Package {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String packageName;

    @Column
    private Double price;

    @ManyToOne
    @JsonBackReference("event-packages")
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToMany(mappedBy ="packages",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference("packages-products")
    private Set<Product> products;

    public Package() {
    }

    public Package(String packageName, Double price) {
        this.packageName = packageName;
        this.price = price;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String name) {
        this.packageName = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
