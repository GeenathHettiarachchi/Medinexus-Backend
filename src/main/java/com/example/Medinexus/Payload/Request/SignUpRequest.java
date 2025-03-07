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
        regexp = "^(?=.[A-Z])(?=.[a-z])(?=.\\d)(?=.[@$!%?&])[A-Za-z\\d@$!%?&]{8,40}$"
    )
    private String password;

    //common fields
    private String fullName;
    private String gender;
    private LocalDate dateOfBirth;
    private String phoneNumber;

    //nurse related fields
    private String specialization;
    private String medicalLicenseNumber;
    private Integer yearsOfExperience;
    private String address;
    private String medicalCertificationFilePath;
    private List<String> availableDays;

    //doctor related fields
    private String clinicAddress;
    private String profilePictureFilePath;

    //pharmacist related fields
    private String pharmacyName;
    private String drugList;

    //patient related fields
    private String bloodGroup;
    private List<String> allergies;
    private List<String> existingMedicalConditions;
    private String emergencyContactName;
    private String emergencyContactNumber;
    private String emergencyContactRelationship;

}