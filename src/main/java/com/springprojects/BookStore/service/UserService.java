package com.springprojects.BookStore.service;

import com.springprojects.BookStore.entity.User;
import com.springprojects.BookStore.entity.VerificationToken;
import com.springprojects.BookStore.model.UserModel;

public interface UserService {
    User registerUser(UserModel user);
    void saveVerificationTokenForUser(String token, User user);

    String validateVerificationToken(String token);

     VerificationToken generateNewVerificationToken(String oldToken);
}
