package com.events.app.controllers;

import com.events.app.dtos.CustomerPackageRequestDTO;
import com.events.app.dtos.EventRequestDTO;
import com.events.app.entities.Event;
import com.events.app.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/addEvent")
    public ResponseEntity<Event> addEvent(@RequestBody EventRequestDTO eventRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.createEvent(eventRequestDTO));
    }

    @PostMapping("/addPackage")
    public ResponseEntity<?> addCustomerPackageToEvent(@RequestBody CustomerPackageRequestDTO customerPackageRequestDTO){
        return ResponseEntity.ok(eventService.addPackageToEvent(customerPackageRequestDTO));
    }
}
