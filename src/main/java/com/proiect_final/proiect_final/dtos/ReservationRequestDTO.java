package com.proiect_final.proiect_final.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class ReservationRequestDTO {

  private LocalDateTime start;
  private LocalDateTime end;

    private Long userId;

   private Integer packageId;

    public ReservationRequestDTO(Set<Long> eventIds, Long userId, LocalDateTime eventDate) {
        this.eventIds = eventIds;
        this.userId = userId;
        this.eventDate = eventDate;
    }

    public Set<Long> getEventIds() {
        return eventIds;
    }

    public void setEventIds(Set<Long> eventIds) {
        this.eventIds = eventIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }
}
