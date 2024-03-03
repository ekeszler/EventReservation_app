package com.proiect_final.proiect_final.controllers;

import com.proiect_final.proiect_final.dtos.CustomerPackageRequestDTO;
import com.proiect_final.proiect_final.dtos.EventRequestDTO;
import com.proiect_final.proiect_final.entities.Event;
import com.proiect_final.proiect_final.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/{name}")
    public ResponseEntity<Event> createEvent(@PathVariable String name){
        return ResponseEntity.ok(eventService.createEvent(name));
    }

    @PostMapping("/{eventID}")
    public ResponseEntity<?> addCustomerPackage(@RequestBody CustomerPackageRequestDTO customerPackageRequestDTO, @PathVariable Long eventId){
        return ResponseEntity.ok(eventService.addPackageToEvent(customerPackageRequestDTO,eventId));
    }
}
