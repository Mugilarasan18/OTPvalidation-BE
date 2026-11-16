package com.mugiloffical.otpvalidation.service;

import com.mugiloffical.otpvalidation.dto.OtpGenerateRequest;
import com.mugiloffical.otpvalidation.dto.OtpResponse;
import com.mugiloffical.otpvalidation.dto.OtpVerifyRequest;
import com.mugiloffical.otpvalidation.entity.OTP;
import com.mugiloffical.otpvalidation.repository.otpRepository;
import org.springframework.stereotype.Service;

import lombok.*;

import java.time.LocalTime;

@Data
@Service
public class OtpService {
    private final otpRepository otpRepository;
    private final emailservice emailservice;

    public OtpService(otpRepository otpRepository,emailservice emailservice){
        this.otpRepository=otpRepository;
        this.emailservice=emailservice;
    }

    public OtpResponse generateotp(OtpGenerateRequest request){
        String otpvalue=generateRandomOtp();
        OTP otp=new OTP();
        otp.setIdentifier(request.getIdentifier());
        otp.setOtp(otpvalue);

        LocalTime now=LocalTime.now();
        otp.setCreatedat(now);
        otp.setExpiresat(now.plusMinutes(3));

        emailservice.SendOtpEmail(request.getIdentifier(),otpvalue);
        System.out.println("OTP email sent successfully");


        otpRepository.save(otp);
        System.out.println("Generated OTP: " + otpvalue);
        System.out.println("OTP saved successfully");
        return new OtpResponse("OTP generated successfully");


    }

    private String generateRandomOtp(){
        int number=100000+(int)(Math.random()*900000);
        return String.valueOf(number);
    }

    public OtpResponse verifyotp(OtpVerifyRequest request){
        OTP otp = (OTP) otpRepository.findByIdentifier(
                request.getIdentifier()
        ).orElseThrow(() -> new RuntimeException("OTP not found"));
        if(LocalTime.now().isAfter(otp.getExpiresat())){
            throw new RuntimeException("OTP expired");
        }
        otpRepository.delete(otp);
        return new OtpResponse("OTP verified Successfully");
    }
}
