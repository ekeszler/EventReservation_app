package com.proiect_final.proiect_final.entities;

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
            name = "customerPackages-product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name="customerPackage_id")
    )
    @JsonBackReference("customerPackages-products")
    private Set<CustomerPackage> customerPackages;

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

    public Set<CustomerPackage> getCustomerPackages() {
        return customerPackages;
    }

    public void setCustomerPackages(Set<CustomerPackage> customerPackages) {
        this.customerPackages = customerPackages;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
