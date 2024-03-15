package com.events.app.mapper;

import com.events.app.dtos.EventRequestDTO;
import com.events.app.entities.Event;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {


    public Event mapFromDTO(EventRequestDTO eventRequestDTO){
        Event event = new Event();
        event.setStart(eventRequestDTO.getStart());
        event.setEnd(eventRequestDTO.getEnd());
        event.setName(eventRequestDTO.getName());
        return event;
    }

}
