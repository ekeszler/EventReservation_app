package com.events.app.dtos;

public class PackageRequestDTO {

    private Long event_id;
    private String packageName;
    private Double price;

    public PackageRequestDTO(){}

    public PackageRequestDTO(Long event_id, String packageName, Double price) {
        this.event_id = event_id;
        this.packageName = packageName;
        this.price = price;
    }

    public Long getEventId() {
        return event_id;
    }

    public void setEventId(Long event_id) {
        this.event_id = event_id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
