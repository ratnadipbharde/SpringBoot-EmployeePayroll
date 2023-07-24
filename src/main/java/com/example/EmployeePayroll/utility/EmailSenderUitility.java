package com.example.EmployeePayroll.utility;

import com.example.EmployeePayroll.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSenderUitility {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    SimpleMailMessage simpleMailMessage;

    public void sendSimpleMail(String emailId, String subject, String body) {
            simpleMailMessage.setTo(emailId);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(body);
           // javaMailSender.send(simpleMailMessage);
    }
}