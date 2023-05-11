package com.springprojects.BookStore.event.listener;

import com.springprojects.BookStore.entity.User;
import com.springprojects.BookStore.event.RegistrationCompleteEvent;
import com.springprojects.BookStore.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Component
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event){
        // Create Verification token link
            User user = event.getUser();
            String token = UUID.randomUUID().toString();
            userService.saveVerificationTokenForUser(token,user);


        String url = event.getApplicationUrl()
                + "/api/register/verifyRegistration?token="
                +token;

        // send mail to user
        log.info("Click the link to verify your account: {}", url);
    }
}
