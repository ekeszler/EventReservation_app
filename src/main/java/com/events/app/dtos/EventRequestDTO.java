package com.events.app.dtos;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Set;

public class EventRequestDTO {


    private String name;

    private LocalDateTime start;

    private LocalDateTime end;

    private Long userId;

    public EventRequestDTO(){}

    public EventRequestDTO( String name, LocalDateTime start, LocalDateTime end, Long userId) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.userId = userId;

    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
