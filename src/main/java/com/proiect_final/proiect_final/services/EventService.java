package com.proiect_final.proiect_final.services;

import com.proiect_final.proiect_final.dtos.CustomerPackageRequestDTO;
import com.proiect_final.proiect_final.dtos.EventRequestDTO;
import com.proiect_final.proiect_final.entities.CustomerPackage;
import com.proiect_final.proiect_final.entities.Event;
import com.proiect_final.proiect_final.entities.User;
import com.proiect_final.proiect_final.exceptions.ResourceNotFoundException;
import com.proiect_final.proiect_final.repositories.CustomerPackageRepository;
import com.proiect_final.proiect_final.repositories.EventRepository;
import com.proiect_final.proiect_final.repositories.ProductRepository;
import com.proiect_final.proiect_final.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EventService {

    EventRepository eventRepository;
    UserRepository userRepository;
    ProductRepository productRepository;

    CustomerPackageRepository customerPackageRepository;


    @Autowired
    public EventService(EventRepository eventRepository, UserRepository userRepository, ProductRepository productRepository, CustomerPackageRepository customerPackageRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.customerPackageRepository = customerPackageRepository;
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
        Event event = new Event();
        event.setStart(eventRequestDTO.getStart());
        event.setEnd(eventRequestDTO.getEnd());
        event.setUser(user);
        return eventRepository.save(event);
    }

    public boolean existsEventBetween(Event event, LocalDateTime start, LocalDateTime end) {

        return event.getStart().isBefore(start) && event.getEnd().isAfter(end)
                || event.getEnd().isAfter(start) && event.getStart().isBefore(end)
                || event.getStart().isEqual(start) || event.getEnd().isEqual(end);
    }

    public CustomerPackage addPackageToEvent(CustomerPackageRequestDTO customerPackageRequestDTO, Long eventId) {
        Event event = eventRepository.findAllById(eventId).orElseThrow(() -> new ResourceNotFoundException("event not found"));
        Optional<CustomerPackage> customerPackages = customerPackageRepository.findByPackageName(customerPackageRequestDTO.getPackageName());
        if(customerPackages.isEmpty()){
            throw new ResourceNotFoundException("Customer package not found");
        }
        CustomerPackage customerPackage = customerPackages.get();
        event.getCustomerPackages().add(customerPackage);
       eventRepository.save(event);
       return customerPackage;
    }


}



