package com.example.Medinexus.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.Medinexus.Model.ContactMessage;
import com.example.Medinexus.Service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(ContactMessage contactMessage) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo("medinexus.msms.0001@gmail.com");  // Recipient's email
        email.setSubject("New Contact Form Submission");
        email.setText(
            "First Name: " + contactMessage.getFirstName() + "\n" +
            "Last Name: " + contactMessage.getLastName() + "\n" +
            "Customer Email: " + contactMessage.getCustomerEmail() + "\n" +
            "Phone Number: " + contactMessage.getPhoneNumber() + "\n" +
            "Message: \n" + contactMessage.getMessage()
        );

        mailSender.send(email);
    }
}