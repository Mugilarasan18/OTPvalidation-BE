package com.mugiloffical.otpvalidation.controller;

import com.mugiloffical.otpvalidation.dto.OtpVerifyRequest;
import com.mugiloffical.otpvalidation.service.OtpService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mugiloffical.otpvalidation.dto.OtpGenerateRequest;
import com.mugiloffical.otpvalidation.dto.OtpResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/otp")
public class OtpController {
    private final OtpService OtpService;
    public OtpController(OtpService OtpService){
        this.OtpService=OtpService;
    }

    @PostMapping("/generate")
    public ResponseEntity<OtpResponse> generateOtp(@RequestBody OtpGenerateRequest request){
        OtpResponse otpResponse=OtpService.generateotp(request);
        return ResponseEntity.ok(otpResponse);
    }
    @PostMapping("/verify")
    public ResponseEntity<OtpResponse> verifyOtp(@RequestBody OtpVerifyRequest request) {
        OtpResponse otpResponse = OtpService.verifyotp(request);
        return ResponseEntity.ok(otpResponse);
    }
}
