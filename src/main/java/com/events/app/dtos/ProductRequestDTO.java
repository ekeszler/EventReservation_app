package com.events.app.dtos;

public class ProductRequestDTO {

    private String productName;

    private Double price;

    private Long packageId;


    public ProductRequestDTO(){}

    public ProductRequestDTO(String productName, Double price, Long packageId) {
        this.productName = productName;
        this.price = price;
        this.packageId = packageId;
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

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }
}
