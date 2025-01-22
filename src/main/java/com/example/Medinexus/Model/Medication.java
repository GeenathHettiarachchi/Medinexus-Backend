package com.example.Medinexus.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "medications")
@Data
public class Medication {
    private String doctorId;
    private String patientId;
    private String pharmasistId;
}
