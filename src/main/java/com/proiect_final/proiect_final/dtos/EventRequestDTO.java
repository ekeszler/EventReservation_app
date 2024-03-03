package com.proiect_final.proiect_final.dtos;

import java.util.Set;

public class EventRequestDTO {

    private Set<Long> customerPackageIds;

    private Set<Long> eventReservationIds;

    private String name;

    public EventRequestDTO(Set<Long> customerPackageIds, Set<Long> eventReservationIds, String name) {
        this.customerPackageIds = customerPackageIds;
        this.eventReservationIds = eventReservationIds;
        this.name = name;
    }

    public Set<Long> getCustomerPackageIds() {
        return customerPackageIds;
    }

    public void setCustomerPackageIds(Set<Long> customerPackageIds) {
        this.customerPackageIds = customerPackageIds;
    }

    public Set<Long> getEventReservationIds() {
        return eventReservationIds;
    }

    public void setEventReservationIds(Set<Long> eventReservationIds) {
        this.eventReservationIds = eventReservationIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
