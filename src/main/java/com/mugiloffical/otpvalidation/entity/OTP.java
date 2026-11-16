package com.mugiloffical.otpvalidation.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalTime;

@Entity
@Table(name="otp")
@Setter
@Getter

public class OTP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String identifier;
    private String otp;
    private LocalTime createdat;
    private LocalTime expiresat;
}
