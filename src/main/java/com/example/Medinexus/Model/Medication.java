package com.example.Medinexus.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "medications")
@Data
public class Medication {

    @Id
    private String id;
    
    private String doctorId;
    private String patientId;
    private String pharmasistId;
}
