package com.events.app.services;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.io.File;
import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMessage(
            String to, String subject, String text ) throws MessagingException {
        // ...

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
        // ...
    }

    public void SendReminderEmail(String to, String eventName, LocalDateTime eventStart){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Reminder for your event | " + eventName);
        message.setText("This is a reminder for your event: " + eventName + " scheduled at " + eventStart);
        javaMailSender.send(message);
    }


}
