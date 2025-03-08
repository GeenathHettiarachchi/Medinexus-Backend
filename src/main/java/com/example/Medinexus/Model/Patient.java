package com.example.Medinexus.Model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "patients")
@Data
public class Patient {
    private String userId;

    private String fullName;
    private String gender;
    private LocalDate dateOfBirth;
    private String bloodGroup;
    private List<String> allergies;
    private List<String> existingMedicalConditions;
    private String email;
    private String phoneNumber;
    private String address;
    private String emergencyContactName;
    private String emergencyContactNumber;
    private String emergencyContactRelationship;
    private String username;
    private String password;
}
