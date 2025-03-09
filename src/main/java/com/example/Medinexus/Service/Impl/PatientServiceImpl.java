package com.example.Medinexus.Service.Impl;

import java.util.List;
import java.util.Optional;

import com.example.Medinexus.Model.Patient;
import com.example.Medinexus.Model.User;
import com.example.Medinexus.Repository.PatientRepository;
import com.example.Medinexus.Repository.UserRepository;
import com.example.Medinexus.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired 
    private PatientRepository patientRepository; 

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
 
    @Override 
    public Patient savePatient(Patient patient) { 
        return patientRepository.save(patient); 
    }

    @Override 
    public List<Patient> getAllPatients() { 
        return patientRepository.findAll(); 
    } 

    @Override 
    public Patient getPatientById(String id) { 
        Optional<Patient> patient =  patientRepository.findById(id); 
        if(patient.isPresent()){ 
            return patient.get(); 
        }else { 
            throw new RuntimeException("Patient not found"); 
        } 
    } 
 
    @Override 
    public Patient updatePatient(Patient patient, String id) { 
        Patient existingPatient = patientRepository.findById(id).orElseThrow(()-> new RuntimeException()); 
        existingPatient.setFullName(patient.getFullName()); 
        existingPatient.setGender(patient.getGender());
        existingPatient.setDateOfBirth(patient.getDateOfBirth());
        existingPatient.setEmail(patient.getEmail()); 
        existingPatient.setPhoneNumber(patient.getPhoneNumber());
        existingPatient.setAddress(patient.getAddress());
        existingPatient.setBloodGroup(patient.getBloodGroup());
        existingPatient.setAllergies(patient.getAllergies());
        existingPatient.setExistingMedicalConditions(patient.getExistingMedicalConditions());
        existingPatient.setEmergencyContactName(patient.getEmergencyContactName());
        existingPatient.setEmergencyContactNumber(patient.getEmergencyContactNumber());
        existingPatient.setEmergencyContactRelationship(patient.getEmergencyContactRelationship());
        existingPatient.setPassword(patient.getPassword());
        patientRepository.save(existingPatient); 

        User user = userRepository.findById(existingPatient.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update user fields
        user.setUsername(patient.getUsername());
        user.setEmail(patient.getEmail());
        if (patient.getPassword() != null && !patient.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(patient.getPassword())); // Update password
        }
        userRepository.save(user);

        return existingPatient; 
    } 

    @Override
    public void deletePatient(String userId) {
        Patient patient = patientRepository.findById(userId).orElseThrow(()-> new RuntimeException());
        userRepository.deleteById(patient.getUserId());
        patientRepository.deleteById(userId);
    }
}
