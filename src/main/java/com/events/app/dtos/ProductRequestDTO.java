package com.events.app.dtos;

public class ProductRequestDTO {

    private String productName;

    private Double price;




    public ProductRequestDTO(String productName, Double price) {
        this.productName = productName;
        this.price = price;
    }



    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
