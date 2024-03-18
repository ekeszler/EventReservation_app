package com.events.app.services;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.io.File;
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


}
