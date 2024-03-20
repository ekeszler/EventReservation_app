package com.events.app.controllers;

import com.events.app.dtos.CustomerPackageRequestDTO;
import com.events.app.dtos.EventRequestDTO;
import com.events.app.entities.Event;
import com.events.app.services.EventService;
import jakarta.mail.MessagingException;
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
    public ResponseEntity<Event> addEvent(@RequestBody EventRequestDTO eventRequestDTO) {
        try {
            Event event = eventService.createEvent(eventRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(event);
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/addPackage")
    public ResponseEntity<?> addCustomerPackageToEvent(@RequestBody CustomerPackageRequestDTO customerPackageRequestDTO){
        return ResponseEntity.ok(eventService.addPackageToEvent(customerPackageRequestDTO));
    }

    @PostMapping("/addLink")
    public ResponseEntity<?> addLinkToEvent(@RequestBody EventRequestDTO eventRequestDTO, String link) throws MessagingException {
        return ResponseEntity.ok(eventService.addLinkToEvent(eventRequestDTO,link));
    }
}
