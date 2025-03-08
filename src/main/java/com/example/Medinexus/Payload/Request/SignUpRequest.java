package com.example.Medinexus.Payload.Request;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SignUpRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
 
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    
    private Set<String> roles;
    
    @NotBlank
    @Size(min = 8, max = 40)
    @Pattern(
        regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,40}$"
    )
    private String password;

    // Common fields
    private String fullName;
    private String gender;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String address;

    // Nurse-specific fields
    private String specialization;
    private String medicalLicenseNumber;
    private Integer yearsOfExperience;
    private String medicalCertificationFilePath;
    private List<String> availableDays;

    // Patient-specific fields
    private String bloodGroup;
    private List<String> allergies;
    private List<String> existingMedicalConditions;
    private String emergencyContactName;
    private String emergencyContactNumber;
    private String emergencyContactRelationship;

    // Pharmacist-specific fields
    private String pharmacyName;
    private String drugList;

    // Doctor-specific fields
    private String clinicAddress;
    private String profilePictureFilePath;
}