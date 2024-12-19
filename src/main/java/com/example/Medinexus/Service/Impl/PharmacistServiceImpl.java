package com.example.Medinexus.Service.Impl;

import java.util.List;
import java.util.Optional;

import com.example.Medinexus.Model.Pharmacist;
import com.example.Medinexus.Repository.PharmacistRepository;
import com.example.Medinexus.Service.PharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PharmacistServiceImpl implements PharmacistService {
    @Autowired 
    private PharmacistRepository pharmacistRepository; 
 
    @Override 
    public Pharmacist savePharmacist(Pharmacist pharmacist) { 
        return pharmacistRepository.save(pharmacist); 
    }

    @Override 
    public List<Pharmacist> getAllPharmacists() { 
        return pharmacistRepository.findAll(); 
    } 

    @Override 
    public Pharmacist getPharmacistById(String id) { 
        Optional<Pharmacist> pharmacist =  pharmacistRepository.findById(id); 
        if(pharmacist.isPresent()){ 
            return pharmacist.get(); 
        }else { 
            throw new RuntimeException("Pharmacist not found"); 
        } 
    } 
 
    @Override 
    public Pharmacist updatePharmacist(Pharmacist pharmacist, String id) { 
        Pharmacist existingPharmacist = pharmacistRepository.findById(id).orElseThrow(()-> new RuntimeException()); 
        existingPharmacist.setFullName(pharmacist.getFullName()); 
        existingPharmacist.setGender(pharmacist.getGender());
        existingPharmacist.setDateOfBirth(pharmacist.getDateOfBirth());
        existingPharmacist.setEmail(pharmacist.getEmail()); 
        existingPharmacist.setPhoneNumber(pharmacist.getPhoneNumber());
        existingPharmacist.setAddress(pharmacist.getAddress());
        existingPharmacist.setAreasOfExpertise(pharmacist.getAreasOfExpertise());
        existingPharmacist.setPharmacyLicenseNumber(pharmacist.getPharmacyLicenseNumber());
        existingPharmacist.setYearsOfExperience(pharmacist.getYearsOfExperience());
        existingPharmacist.setLanguageProficiency(pharmacist.getLanguageProficiency());
        existingPharmacist.setQualifications(pharmacist.getQualifications());
        existingPharmacist.setPharmacyName(pharmacist.getPharmacyName());
        existingPharmacist.setPharmacyAddress(pharmacist.getPharmacyAddress());
        existingPharmacist.setPreferredWorkLocation(pharmacist.getPreferredWorkLocation());
        existingPharmacist.setPassword(pharmacist.getPassword());
        existingPharmacist.setEmergencyContact(pharmacist.getEmergencyContact());
        existingPharmacist.setAvailableDays(pharmacist.getAvailableDays());
        existingPharmacist.setShiftPreference(pharmacist.getShiftPreference());
        pharmacistRepository.save(existingPharmacist); 
        return existingPharmacist; 
    } 

    @Override 
    public void deletePharmacist(String id) { 
        pharmacistRepository.findById(id).orElseThrow(()-> new RuntimeException()); 
        pharmacistRepository.deleteById(id); 
    }
}