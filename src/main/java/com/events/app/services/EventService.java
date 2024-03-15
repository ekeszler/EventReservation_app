package com.events.app.services;

import com.events.app.dtos.CustomerPackageRequestDTO;
import com.events.app.dtos.EventRequestDTO;
import com.events.app.entities.Event;
import com.events.app.entities.Package;
import com.events.app.entities.User;
import com.events.app.exceptions.ResourceNotFoundException;
import com.events.app.mapper.EventMapper;
import com.events.app.repositories.EventRepository;
import com.events.app.repositories.PackageRepository;
import com.events.app.repositories.ProductRepository;
import com.events.app.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {

    EventRepository eventRepository;
    UserRepository userRepository;
    ProductRepository productRepository;

    PackageRepository packageRepository;

    //TODO de injectat in constructor
    @Autowired
    EventMapper eventMapper;


    @Autowired
    public EventService(EventRepository eventRepository, UserRepository userRepository, ProductRepository productRepository, PackageRepository packageRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.packageRepository = packageRepository;
    }

    @Transactional
    public Event createEvent(EventRequestDTO eventRequestDTO) {
        User user = userRepository.findById(eventRequestDTO.getUserId()).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        List<Event> scheduledEvent = eventRepository.findAllByName(eventRequestDTO.getName());

        for (Event event : scheduledEvent) {
            if (existsEventBetween(event, eventRequestDTO.getStart(), eventRequestDTO.getEnd())) {
                throw new ResourceNotFoundException("this date is already reserved");
            }
        }
//        boolean existsEventBetween = scheduledEvent.stream()
//                .anyMatch(event -> existsEventBetween(event, eventRequestDTO.getStart(), eventRequestDTO.getEnd()));
//        if (existsEventBetween){
//            //throw exception
//        }

        Event event = eventMapper.mapFromDTO(eventRequestDTO);
        event.setUser(user);
        return eventRepository.save(event);
    }

    public boolean existsEventBetween(Event event, LocalDateTime start, LocalDateTime end) {

        return event.getStart().isBefore(start) && event.getEnd().isAfter(end)
                || event.getEnd().isAfter(start) && event.getStart().isBefore(end)
                || event.getStart().isEqual(start) || event.getEnd().isEqual(end);
    }

    public Package addPackageToEvent(CustomerPackageRequestDTO customerPackageRequestDTO) {
        Event event = eventRepository.findById(customerPackageRequestDTO.getEventId()).orElseThrow(() -> new ResourceNotFoundException("event not found"));
        Package customerPackages = packageRepository.findByPackageName(customerPackageRequestDTO.getPackageName()).orElseThrow(() ->  new ResourceNotFoundException("Customer package not found"));;
        customerPackages.setEvent(event);
        return packageRepository.save(customerPackages);
    }
}



