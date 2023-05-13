package com.springprojects.BookStore.repository;

import com.springprojects.BookStore.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

     VerificationToken findByToken(String token);
}
