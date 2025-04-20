package com.guerra.picpay.picpay.services;

import com.guerra.picpay.picpay.DTO.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Async
    public void sendEmail(EmailDTO email) throws Exception {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sender);
            message.setTo(email.email());
            message.setSubject(email.subject());
            message.setText(email.message());

            mailSender.send(message);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
