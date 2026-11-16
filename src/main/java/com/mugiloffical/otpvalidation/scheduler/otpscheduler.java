package com.mugiloffical.otpvalidation.scheduler;

import com.mugiloffical.otpvalidation.entity.OTP;
import com.mugiloffical.otpvalidation.repository.otpRepository;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.time.LocalTime;

@Component
public class otpscheduler {
    private final otpRepository otpRepository;
    public otpscheduler(otpRepository otpRepository){
        this.otpRepository=otpRepository;
    }

    @Scheduled(fixedRate = 5000)
    public void deleteexpiredotp(){
            System.out.println("Scheduler Running: " + LocalTime.now());
            List<OTP> expiredotp =
                    otpRepository.findByExpiresatBefore(LocalTime.now());
            System.out.println("Expired Count: " + expiredotp.size());
            otpRepository.deleteAll(expiredotp);
    }
}
