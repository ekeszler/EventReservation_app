package com.events.app.services;

import com.events.app.dtos.PackageRequestDTO;
import com.events.app.dtos.EventRequestDTO;
import com.events.app.dtos.ProductRequestDTO;
import com.events.app.entities.Event;
import com.events.app.entities.Package;
import com.events.app.entities.Product;
import com.events.app.entities.User;
import com.events.app.exceptions.ResourceNotFoundException;
import com.events.app.mapper.EventMapper;
import com.events.app.mapper.UserMapper;
import com.events.app.repositories.EventRepository;
import com.events.app.repositories.PackageRepository;
import com.events.app.repositories.ProductRepository;
import com.events.app.repositories.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {

    EventRepository eventRepository;
    UserRepository userRepository;
    ProductRepository productRepository;
    PackageRepository packageRepository;
    EmailService emailService;
    EventMapper eventMapper;
    UserMapper userMapper;
    //UserService userService;


    @Autowired
    public EventService(EventRepository eventRepository, UserRepository userRepository, ProductRepository productRepository, PackageRepository packageRepository,
                        EventMapper eventMapper, EmailService emailService, UserMapper userMapper, UserService userService) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.packageRepository = packageRepository;
        this.eventMapper = eventMapper;
        this.emailService = emailService;
        this.userMapper = userMapper;
        //this.userService = userService;
    }

    @Transactional
    public Event createEvent(EventRequestDTO eventRequestDTO) throws MessagingException {
        User user = userRepository.findUserByUserName(userMapper.getLoggedInUsername()).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        List<Event> scheduledEvent = eventRepository.findAllByName(eventRequestDTO.getName());

        for (Event event : scheduledEvent) {
            if (existsEventBetween(event, eventRequestDTO.getStart(), eventRequestDTO.getEnd())) {
                throw new ResourceNotFoundException("this date is already reserved");
            }
        }

        emailService.sendMessage(user.getEmail(), "Reservation for " + eventRequestDTO.getStart(), "Your reservation was successfully done");

        Event event = eventMapper.mapFromDTO(eventRequestDTO);
        event.setUser(user);
        return eventRepository.save(event);


    }


    public boolean existsEventBetween(Event event, LocalDateTime start, LocalDateTime end) {

        return event.getStart().isBefore(start) && event.getEnd().isAfter(end)
                || event.getEnd().isAfter(start) && event.getStart().isBefore(end)
                || event.getStart().isEqual(start) || event.getEnd().isEqual(end);
    }

    @Transactional
    public Package addPackageToEvent(PackageRequestDTO packageRequestDTO) {
        Event event = eventRepository.findById(packageRequestDTO.getEventId()).orElseThrow(() -> new ResourceNotFoundException("event not found"));
        Package customerPackages = packageRepository.findByPackageName(packageRequestDTO.getPackageName()).orElseThrow(() -> new ResourceNotFoundException("Customer package not found"));
        customerPackages.setEvent(event);
        return packageRepository.save(customerPackages);
    }

    @Transactional
    public Event addLinkToEvent(EventRequestDTO eventRequestDTO, String link) throws MessagingException {
        Event event = eventRepository.findByName(eventRequestDTO.getName()).orElseThrow(() -> new ResourceNotFoundException("event not found"));
        User user = userRepository.findUserByUserName(userMapper.getLoggedInUsername()).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        event.setGaleryLink(link);
        emailService.sendMessage(user.getEmail(), "Gallery link for " + event.getName(), "You can now download you content for " + event.getName() + " from the following link " + link);
        return eventRepository.save(event);

    }

    @Transactional
    public List<Event> findAllEvents(){
        return eventRepository.findAll();
    }

    @Transactional
    public Event updateEventDate(Long eventId, LocalDateTime newStart) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new ResourceNotFoundException("Event not found"));
        event.setStart(newStart);
        return eventRepository.save(event);
    }

    @Transactional
    public void deleteEvent(Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new ResourceNotFoundException("event not found"));
        eventRepository.delete(event);
    }
}



