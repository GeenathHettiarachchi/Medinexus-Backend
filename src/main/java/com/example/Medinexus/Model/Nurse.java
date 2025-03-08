package com.example.Medinexus.Model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "nurses")
@Data
public class Nurse {
    private String userId;

    private String fullName;
    private String gender;
    private LocalDate dateOfBirth;
    private String specialization;
    private String medicalLicenseNumber;
    private Integer yearsOfExperience;
    private String email;
    private String phoneNumber;
    private String address;
    private String username;
    private String password;
    private String medicalCertificationFilePath;
    private List<String> availableDays;
}
