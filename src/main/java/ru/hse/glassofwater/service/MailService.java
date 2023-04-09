package ru.hse.glassofwater.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;

    public boolean sendMessage(String email, String code){
        SimpleMailMessage simpleMail = new SimpleMailMessage();
        simpleMail.setTo(email);
        simpleMail.setSubject("Glass of water code");
        simpleMail.setText(String.format("Hello, driver!\n\nYour login code: %s\n\nBest regards,\nGlass Of Water team.", code));

        try {
            mailSender.send(simpleMail);
        } catch (MailException e){
            return false;
        }
        return true;
    }
}
