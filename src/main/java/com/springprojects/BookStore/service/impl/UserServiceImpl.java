package com.springprojects.BookStore.service.impl;

import com.springprojects.BookStore.entity.User;
import com.springprojects.BookStore.model.UserModel;
import com.springprojects.BookStore.repository.UserRepository;
import com.springprojects.BookStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository _userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public User registerUser(UserModel userModel){

        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setUsername(userModel.getUserName());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));

        _userRepository.save(user);

        return user;

    }
}
