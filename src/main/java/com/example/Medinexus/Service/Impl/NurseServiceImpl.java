package com.example.Medinexus.Service.Impl;

import java.util.List;
import java.util.Optional;

import com.example.Medinexus.Model.Nurse;
import com.example.Medinexus.Repository.NurseRepository;
import com.example.Medinexus.Service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NurseServiceImpl implements NurseService {
    @Autowired 
    private NurseRepository nurseRepository; 
 
    @Override 
    public Nurse saveNurse(Nurse nurse) { 
        return nurseRepository.save(nurse); 
    }

    @Override 
    public List<Nurse> getAllNurses() { 
        return nurseRepository.findAll(); 
    } 

    @Override 
    public Nurse getNurseById(String id) { 
        Optional<Nurse> nurse =  nurseRepository.findById(id); 
        if(nurse.isPresent()){ 
            return nurse.get(); 
        }else { 
            throw new RuntimeException("Nurse not found"); 
        } 
    } 
 
    @Override 
    public Nurse updateNurse(Nurse nurse, String id) { 
        Nurse existingNurse = nurseRepository.findById(id).orElseThrow(()-> new RuntimeException()); 
        existingNurse.setFullName(nurse.getFullName()); 
        existingNurse.setGender(nurse.getGender());
        existingNurse.setDateOfBirth(nurse.getDateOfBirth());
        existingNurse.setEmail(nurse.getEmail());
        existingNurse.setPhoneNumber(nurse.getPhoneNumber());
        existingNurse.setAddress(nurse.getAddress());
        existingNurse.setSpecialization(nurse.getSpecialization()); 
        existingNurse.setMedicalLicenseNumber(nurse.getMedicalLicenseNumber());
        existingNurse.setYearsOfExperience(nurse.getYearsOfExperience());
        existingNurse.setClinicOrHospitalAddress(nurse.getClinicOrHospitalAddress());
        existingNurse.setPreferredContactMethod(nurse.getPreferredContactMethod());
        existingNurse.setLanguageProficiency(nurse.getLanguageProficiency());
        existingNurse.setQualifications(nurse.getQualifications());
        existingNurse.setPassword(nurse.getPassword());
        existingNurse.setAvailableDays(nurse.getAvailableDays());
        existingNurse.setShiftPreference(nurse.getShiftPreference());
        nurseRepository.save(existingNurse); 
        return existingNurse; 
    } 

    @Override 
    public void deleteNurse(String id) { 
        nurseRepository.findById(id).orElseThrow(()-> new RuntimeException()); 
        nurseRepository.deleteById(id); 
    }
}
