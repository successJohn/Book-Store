package com.springprojects.BookStore.controller;

import com.springprojects.BookStore.entity.User;
import com.springprojects.BookStore.event.RegistrationCompleteEvent;
import com.springprojects.BookStore.model.UserModel;
import com.springprojects.BookStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"api/register"})
public class RegistrationController {

    private final UserService _service;


    public RegistrationController(UserService service){
        this._service =service;
    }

    @PostMapping
    public String registerUser(@RequestBody UserModel userModel){
        User user = _service.registerUser(userModel);

        return "Success";
    }

}
