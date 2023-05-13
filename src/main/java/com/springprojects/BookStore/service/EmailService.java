package com.springprojects.BookStore.service;

import com.springprojects.BookStore.entity.User;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
     void sendEmail(User user,String url);


}
