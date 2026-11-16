package com.mugiloffical.otpvalidation.dto;


import lombok.*;

@Getter
@Setter
public class OtpVerifyRequest {
    private String identifier;
    private String otp;

}
