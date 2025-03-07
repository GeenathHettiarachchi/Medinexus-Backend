package com.example.Medinexus.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Medinexus.Model.ContactMessage;
import com.example.Medinexus.Service.EmailService;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*")
public class ContactController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@Valid @RequestBody ContactMessage contactMessage) {
        emailService.sendEmail(contactMessage);
        return ResponseEntity.ok("Email sent successfully!");
    }
}