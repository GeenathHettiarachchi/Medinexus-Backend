package com.example.Medinexus.Model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "admin")
@Data
public class Admin {
    private String userId;
    private String adminName;
    private String adminAddress;
    private String adminPhone;
    private String email;
    private String username;
    private String password;
}
