package com.chatapp.chatapp.Dataloader.repository;

import com.chatapp.chatapp.Dataloader.Entity.OtpToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpTokenRepository extends JpaRepository<OtpToken, Long> {
    OtpToken findByEmail(String email);
    OtpToken findByEmailAndOtp(String email, String otp);
}
