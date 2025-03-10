package com.example.Medinexus.Model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "appointments")
@Data
public class Appointment {

    @Id
    private String id;
    
    private String doctorId;
    private String nurseId;
    private LocalDate date;
    private LocalTime time;
    private String patientName;
    private String patientContact;
    private String patientEmail;
}
