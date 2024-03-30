package com.events.app.controllers;

import com.events.app.dtos.PackageRequestDTO;
import com.events.app.dtos.EventRequestDTO;
import com.events.app.entities.Event;
import com.events.app.entities.Product;
import com.events.app.services.EventService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
    public ResponseEntity<?> addCustomerPackageToEvent(@RequestBody PackageRequestDTO packageRequestDTO){
        return ResponseEntity.ok(eventService.addPackageToEvent(packageRequestDTO));
    }

    @PostMapping("/addLink")
    public ResponseEntity<?> addLinkToEvent(@RequestBody EventRequestDTO eventRequestDTO, String link) throws MessagingException {
        return ResponseEntity.ok(eventService.addLinkToEvent(eventRequestDTO,link));
    }

    @GetMapping
    public ResponseEntity<List<Event>> findAllEvents(){
        List<Event> events = eventService.findAllEvents();
        return ResponseEntity.ok(events);
    }

    @PutMapping("/{eventId}/update-start")
    public ResponseEntity<String> updateEventStart(@PathVariable Long eventId, @RequestParam LocalDateTime newStart){
        try{
            eventService.updateEventDate(eventId, newStart);
            return ResponseEntity.ok("The start of event with id " + eventId + " was updated to " + newStart );
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong. The start was not updated");
        }
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long eventId){
        try{
            eventService.deleteEvent(eventId);
            return ResponseEntity.ok("The event with id " + eventId + " was deleted");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong, event was not deleted");
        }
    }
}
