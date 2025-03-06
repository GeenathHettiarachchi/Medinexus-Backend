package com.example.Medinexus.Model;

import lombok.Data;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pharmacists")
@Data
public class Pharmacist {
    @Id
    private String id;

    private String fullName;
    private String gender;
    private LocalDate dateOfBirth;
    private String email;
    private String phoneNumber;
    private String address;
    private String specialization;
    private String pharmacyLicenseNumber;
    private Integer yearsOfExperience;
    private String pharmacyName;
    private String username;
    private String password;
    private String licenseFilePath;
    private String drugList;
}