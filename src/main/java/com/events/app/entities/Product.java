package com.events.app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String productName;

    @Column
    private Double price;

    @ManyToMany
    @JoinTable(
            name = "packages-product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name="package_id")
    )
    @JsonBackReference("packages-products")
    private Set<Package> packages;

    public Product(String productName, Double price) {
        this.productName = productName;
        this.price = price;

    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String name) {
        this.productName = name;
    }

    public Set<Package> getCustomerPackages() {
        return packages;
    }

    public void setCustomerPackages(Set<Package> aPackages) {
        this.packages = aPackages;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
