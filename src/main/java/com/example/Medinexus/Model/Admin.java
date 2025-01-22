package com.example.Medinexus.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "admin")
@Data
public class Admin {

    @Id
    private String adminId;
    private String adminName;
    private String adminAddress;
    private String adminPhone;
    private String adminEmail;
    private String adminPassword;
}
