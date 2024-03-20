package com.events.app.dtos;

public class PackageRequestDTO {

    private Long eventId;
    private String packageName;
    private Double price;

    public PackageRequestDTO(Long eventId, String packageName, Double price) {
        this.eventId = eventId;
        this.packageName = packageName;
        this.price = price;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
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
