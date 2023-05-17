package com.springprojects.BookStore.service.impl;

import com.springprojects.BookStore.entity.User;
import com.springprojects.BookStore.entity.VerificationToken;
import com.springprojects.BookStore.model.UserModel;
import com.springprojects.BookStore.repository.UserRepository;
import com.springprojects.BookStore.repository.VerificationTokenRepository;
import com.springprojects.BookStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository _userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
@Autowired
    private VerificationTokenRepository _verificationTokenRepository;


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

    public void saveVerificationTokenForUser(String token, User user){
        VerificationToken verificationToken = new VerificationToken(user, token);
        _verificationTokenRepository.save(verificationToken);
    }

    public String validateVerificationToken(String token){
        VerificationToken verificationToken = _verificationTokenRepository.findByToken(token);

        if(verificationToken == null) {
            return "invalid";
        }
        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();

        if((verificationToken.getExpirationTime().getTime() - cal.getTime().getTime()) <= 0){
            _verificationTokenRepository.delete(verificationToken);
            return "expired";
        }

        user.setEnabled(true);
        _userRepository.save(user);
        return "valid";

    }

    public VerificationToken generateNewVerificationToken(String oldToken){
        VerificationToken verificationToken = _verificationTokenRepository.findByToken(oldToken);

        verificationToken.setToken(UUID.randomUUID().toString());
        _verificationTokenRepository.save(verificationToken);
        return verificationToken;
    }


}
