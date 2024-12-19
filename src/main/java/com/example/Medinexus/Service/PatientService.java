package com.example.Medinexus.Service;

import java.util.List;

import com.example.Medinexus.Model.Patient;

public interface PatientService {
    Patient savePatient(Patient patient); 
    List<Patient> getAllPatients(); 
    Patient getPatientById(String id); 
    Patient updatePatient(Patient patient, String id); 
    void deletePatient(String id);
}
