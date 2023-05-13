package com.springprojects.BookStore.controller;

import com.springprojects.BookStore.entity.User;
import com.springprojects.BookStore.event.RegistrationCompleteEvent;
import com.springprojects.BookStore.model.UserModel;
import com.springprojects.BookStore.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"api/register"})
public class RegistrationController {

    private final UserService _service;

    @Autowired
    private ApplicationEventPublisher publisher;

    public RegistrationController(UserService service){
        this._service =service;
    }

    @PostMapping
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request){
        User user = _service.registerUser(userModel);
        //return new ResponseEntity(user, HttpStatus.CREATED);
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return "Success";
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token){
        String result = _service.validateVerificationToken(token);
        if(result.equalsIgnoreCase("valid")){
            return "User Verified Successfully";
        }else{
            return "Bad User";
        }
    }
    private String applicationUrl(HttpServletRequest request){
        return "http://"+
                request.getServerName() +
                ":"+
                request.getServerPort() +
                request.getContextPath();
    }

}
