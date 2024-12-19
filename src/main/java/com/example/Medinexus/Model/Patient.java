package com.example.Medinexus.Model;

import lombok.Data;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "patients")
@Data
public class Patient {
    @Id
    private String id;

    private String fullName;
    private String gender;
    private String dateOfBirth;
    private String email;
    private String phoneNumber;
    private String address;
    private String nationalIdOrPassportNumber;
    private String bloodGroup;
    private List<String> allergies;
    private List<String> existingMedicalConditions;
    private double height;
    private double weight;
    private String anySpecialNotice;
    private String emergencyContactName;
    private String emergencyContactRelationship;
    private String emergencyContactNumber;
    private String username;
    private String password;
    private List<String> medicalReportsFilePath;
    private String profilePictureFilePath;
    private String anySpecialRequirements;
}
