package com.example.Medinexus.Service.Impl;

import java.util.List;
import java.util.Optional;

import com.example.Medinexus.Model.Pharmacist;
import com.example.Medinexus.Model.User;
import com.example.Medinexus.Repository.PharmacistRepository;
import com.example.Medinexus.Repository.UserRepository;
import com.example.Medinexus.Service.PharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PharmacistServiceImpl implements PharmacistService {
    @Autowired 
    private PharmacistRepository pharmacistRepository; 

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
 
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
        existingPharmacist.setMedicalLicenseNumber(pharmacist.getMedicalLicenseNumber());
        existingPharmacist.setYearsOfExperience(pharmacist.getYearsOfExperience());
        existingPharmacist.setPharmacyName(pharmacist.getPharmacyName());
        existingPharmacist.setPassword(passwordEncoder.encode(pharmacist.getPassword()));
        pharmacistRepository.save(existingPharmacist); 

        User user = userRepository.findById(existingPharmacist.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update user fields
        user.setUsername(pharmacist.getUsername());
        user.setEmail(pharmacist.getEmail());
        if (pharmacist.getPassword() != null && !pharmacist.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(pharmacist.getPassword())); // Update password
        }
        userRepository.save(user);

        return existingPharmacist; 
    } 

    @Override
    public void deletePharmacist(String userId) {
        Pharmacist pharmacist = pharmacistRepository.findById(userId).orElseThrow(()-> new RuntimeException());
        userRepository.deleteById(pharmacist.getUserId());
        pharmacistRepository.deleteById(userId);
    }
}