package com.mugiloffical.otpvalidation.repository;

import com.mugiloffical.otpvalidation.entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface otpRepository extends JpaRepository<OTP,Long> {
    Optional<OTP> findByIdentifier(String identifier);
    List<OTP> findByExpiresatBefore(LocalTime time);

}
