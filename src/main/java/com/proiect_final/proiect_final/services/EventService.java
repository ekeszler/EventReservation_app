package com.proiect_final.proiect_final.services;

import com.proiect_final.proiect_final.entities.CustomerPackage;
import com.proiect_final.proiect_final.entities.Event;
import com.proiect_final.proiect_final.entities.Reservation;
import com.proiect_final.proiect_final.repositories.EventRepository;
import com.proiect_final.proiect_final.repositories.ProductRepository;
import com.proiect_final.proiect_final.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {

    EventRepository eventRepository;
    UserRepository userRepository;
    ProductRepository productRepository;


    @Autowired
    public EventService(EventRepository eventRepository, UserRepository userRepository,  ProductRepository productRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public Event createEvent(String eventName){
        Event event = new Event(eventName);
        return eventRepository.save(event);
    }

    //public CustomerPackage addPackageToEvent()



}
