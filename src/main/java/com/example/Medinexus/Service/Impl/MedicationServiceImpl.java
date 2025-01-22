package com.example.Medinexus.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Medinexus.Model.Medication;
import com.example.Medinexus.Repository.MedicationRepository;
import com.example.Medinexus.Service.MedicationService;

@Service
public class MedicationServiceImpl implements MedicationService {
    @Autowired
    private MedicationRepository medicationRepository; 
 
    @Override 
    public Medication saveMedication(Medication medication) { 
        return medicationRepository.save(medication); 
    }

    @Override 
    public List<Medication> getAllMedications() { 
        return medicationRepository.findAll(); 
    } 

    @Override 
    public Medication getMedicationById(String id) { 
        Optional<Medication> medication =  medicationRepository.findById(id); 
        if(medication.isPresent()){ 
            return medication.get(); 
        }else { 
            throw new RuntimeException("Appointment not found"); 
        } 
    } 
 
    @Override 
    public Medication updateMedication(Medication medication, String id) { 
        Medication existingMedication = medicationRepository.findById(id).orElseThrow(()-> new RuntimeException()); 
        existingMedication.setDoctorId(medication.getDoctorId()); 
        existingMedication.setPharmasistId(medication.getPharmasistId());
        existingMedication.setPatientId(medication.getPatientId());
        medicationRepository.save(existingMedication); 
        return existingMedication; 
    } 

    @Override 
    public void deleteMedication(String id) { 
        medicationRepository.findById(id).orElseThrow(()-> new RuntimeException("Medication not found")); 
        medicationRepository.deleteById(id);
    }
}