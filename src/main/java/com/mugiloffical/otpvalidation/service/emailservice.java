package com.mugiloffical.otpvalidation.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class emailservice {
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String sendermail;
    public emailservice(JavaMailSender mailSender){
        this.mailSender=mailSender;
    }
    public void SendOtpEmail(String recievermail,String otp){
        SimpleMailMessage mess=new SimpleMailMessage();
        System.out.println("Sender : " + sendermail);
        System.out.println("Receiver : " + recievermail);
        mess.setFrom(sendermail);
        mess.setTo(recievermail);
        mess.setSubject("OTP for verification");
        mess.setText("Hello\n\n"+"Your OTP for verification is: "+otp+"\n\nPlease don't share this OTP to anyone");
        mailSender.send(mess);
    }


}
