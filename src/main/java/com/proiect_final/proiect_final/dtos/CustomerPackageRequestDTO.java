package com.proiect_final.proiect_final.dtos;

import java.util.Set;

public class CustomerPackageRequestDTO {

    private Set<Long> eventIds;

    private Set<Long> productIds;

    public CustomerPackageRequestDTO(Set<Long> eventIds, Set<Long> productIds) {
        this.eventIds = eventIds;
        this.productIds = productIds;
    }

    public Set<Long> getEventIds() {
        return eventIds;
    }

    public void setEventIds(Set<Long> eventIds) {
        this.eventIds = eventIds;
    }

    public Set<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(Set<Long> productIds) {
        this.productIds = productIds;
    }
}
