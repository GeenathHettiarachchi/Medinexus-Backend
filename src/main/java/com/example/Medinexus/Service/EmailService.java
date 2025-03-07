package com.example.Medinexus.Service;

import com.example.Medinexus.Model.ContactMessage;

public interface EmailService {
    void sendEmail(ContactMessage contactMessage);
}