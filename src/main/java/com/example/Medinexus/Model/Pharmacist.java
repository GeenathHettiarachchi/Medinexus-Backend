package com.example.Medinexus.Model;

import lombok.Data;
import java.time.LocalDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pharmacists")
@Data
public class Pharmacist {
    private String userId;

    private String fullName;
    private String gender;
    private LocalDate dateOfBirth;
    private String medicalLicenseNumber;
    private Integer yearsOfExperience;
    private String pharmacyName;
    private String specialization;
    private String email;
    private String phoneNumber;
    private String address;
    private String username;
    private String password;
    private String medicalCertificationFilePath;
    private String drugList;
}