package com.springprojects.BookStore.service.impl;

import com.springprojects.BookStore.entity.User;
import com.springprojects.BookStore.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.beans.SimpleBeanInfo;
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(User user,String url){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("johnoluwadara@gmail.com");
        mailMessage.setTo(user.getEmail());
        mailMessage.setText("To confirm your account, please click here" + url );

        javaMailSender.send(mailMessage);
    }
}
