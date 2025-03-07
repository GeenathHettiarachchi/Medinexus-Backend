package com.example.Medinexus.Model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "nurses")
@Data
public class Nurse {
    @Id
    private String id;
    private String userId; // Reference to the User collection

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
