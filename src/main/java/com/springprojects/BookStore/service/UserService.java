package com.springprojects.BookStore.service;

import com.springprojects.BookStore.entity.User;
import com.springprojects.BookStore.model.UserModel;

public interface UserService {
     User registerUser(UserModel user);
}
