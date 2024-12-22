package com.example.Medinexus.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "appointments")
@Data
public class Appointment {
    private String doctorId;
    private String patientId;
    private String nurseId;
}
