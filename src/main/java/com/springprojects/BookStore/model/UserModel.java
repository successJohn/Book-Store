package com.springprojects.BookStore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String userName;
    public String matchingPassword;
}
