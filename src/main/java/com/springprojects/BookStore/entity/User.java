package com.springprojects.BookStore.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @Column(
            nullable = false
    )
    public String firstName;
    @Column(
            nullable = false
    )
    public String lastName;
    public String email;
    @Column(
            nullable = false,
            unique = true
    )
    public String username;
    @Column(length = 60)
    public String password;
    private boolean enabled = false;
}
