package com.events.app.services;

import com.events.app.entities.Event;
import com.events.app.entities.User;
import com.events.app.repositories.EventRepository;
import jakarta.mail.MessagingException;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventReminderService {

    EmailService emailService;
    EventRepository eventRepository;

    @Autowired
    public EventReminderService(EmailService emailService, EventRepository eventRepository) {
        this.emailService = emailService;
        this.eventRepository = eventRepository;
    }

    @Scheduled(cron = "0 0 10 * * ?")
    public void sendEventReminder(){
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime oneWeekBefore = today.plusWeeks(1);
        LocalDateTime threeDaysBefore = today.plusDays(3);
        LocalDateTime oneDayBefore = today.plusDays(1);


        List<Event> events = eventRepository.findAll();

        for(Event scheduledEvent : events){
            LocalDateTime eventStart = scheduledEvent.getStart();
            User user = scheduledEvent.getUser();

            if(eventStart.equals(oneWeekBefore) || eventStart.equals(threeDaysBefore) || eventStart.equals(oneDayBefore)){
                String userEmail = user.getEmail();
                emailService.SendReminderEmail(userEmail, scheduledEvent.getName(), scheduledEvent.getStart());
            }

        }
    }

    @Scheduled(cron = "0 0 10 * * ?")
    public void sendReviewReminder() throws MessagingException {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime oneDayAfter = today.minusDays(1);

        List<Event> events = eventRepository.findAll();

        for(Event pastEvent : events){
            LocalDateTime eventEnd = pastEvent.getEnd();
            User user = pastEvent.getUser();
            if(pastEvent.getReview() == (null)) {
                if (eventEnd.equals(oneDayAfter)) {
                    String userEmail = user.getEmail();
                    emailService.sendMessage(userEmail, "Please write a review for " + pastEvent.getName(), "Please write a review about your event");
                }
            }
        }
    }


}
