package com.example.Medinexus.Model;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;
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
    private List<String> areasOfExpertise;
    private String pharmacyLicenseNumber;
    private Integer yearsOfExperience;
    private List<String> languageProficiency;
    private List<String> qualifications;
    private String pharmacyName;
    private String pharmacyAddress;
    private String preferredWorkLocation;
    private String username;
    private String password;
    private String emergencyContact;
    private String certificateOrLicenseFilePath;
    private String profilePictureFilePath;
    private List<String> availableDays;
    private List<String> shiftPreference;
}