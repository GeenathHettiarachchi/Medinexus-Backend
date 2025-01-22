package com.example.Medinexus.Service;

import java.util.List;

import com.example.Medinexus.Model.Medication;

public interface MedicationService {
    Medication saveMedication(Medication appointment); 
    List<Medication> getAllMedications(); 
    Medication getMedicationById(String id); 
    Medication updateMedication(Medication medication, String id); 
    void deleteMedication(String id);
}
