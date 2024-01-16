package com.example.academify.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationCode(String userEmail, String verificationCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userEmail);
        message.setSubject("Doğrulama Kodu");
        message.setText("E-posta doğrulama kodunuz: " + verificationCode);
        mailSender.send(message);
    }

    public void sendPasswordResetCode(String userEmail, String resetCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userEmail);
        message.setSubject("Şifre Sıfırlama Kodu");
        message.setText("Şifre sıfırlama kodunuz: " + resetCode);
        mailSender.send(message);
    }
}