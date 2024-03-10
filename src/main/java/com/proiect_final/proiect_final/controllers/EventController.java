package com.proiect_final.proiect_final.controllers;

import com.proiect_final.proiect_final.dtos.CustomerPackageRequestDTO;
import com.proiect_final.proiect_final.dtos.EventRequestDTO;
import com.proiect_final.proiect_final.dtos.UserRequestDTO;
import com.proiect_final.proiect_final.entities.Event;
import com.proiect_final.proiect_final.entities.User;
import com.proiect_final.proiect_final.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/event")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/{name}")
    public ResponseEntity<Event> addEvent(@RequestBody EventRequestDTO eventRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.createEvent(eventRequestDTO));
    }

    @PostMapping("/{packageName},/event/{eventId}")
    public ResponseEntity<?> addCustomerPackageToEvent(@RequestBody CustomerPackageRequestDTO customerPackageRequestDTO, @PathVariable Long eventId){
        return ResponseEntity.ok(eventService.addPackageToEvent(customerPackageRequestDTO,eventId));
    }
}
