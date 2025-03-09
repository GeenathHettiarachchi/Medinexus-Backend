package com.example.Medinexus.Service.Impl;

import java.util.List;
import java.util.Optional;

import com.example.Medinexus.Model.Nurse;
import com.example.Medinexus.Model.User;
import com.example.Medinexus.Repository.NurseRepository;
import com.example.Medinexus.Repository.UserRepository;
import com.example.Medinexus.Service.NurseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class NurseServiceImpl implements NurseService {
    @Autowired 
    private NurseRepository nurseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Nurse saveNurse(Nurse nurse) {
        return nurseRepository.save(nurse);
    }

    @Override 
    public List<Nurse> getAllNurses() { 
        return nurseRepository.findAll(); 
    } 

    @Override 
    public Nurse getNurseById(String userId) { 
        Optional<Nurse> nurse =  nurseRepository.findById(userId); 
        if(nurse.isPresent()){ 
            return nurse.get(); 
        }else { 
            throw new RuntimeException("Nurse not found"); 
        } 
    } 
 
    @Override 
    public Nurse updateNurse(Nurse nurse, String userId) { 
        Nurse existingNurse = nurseRepository.findById(userId).orElseThrow(()-> new RuntimeException()); 
        existingNurse.setFullName(nurse.getFullName()); 
        existingNurse.setGender(nurse.getGender());
        existingNurse.setDateOfBirth(nurse.getDateOfBirth());
        existingNurse.setEmail(nurse.getEmail());
        existingNurse.setPhoneNumber(nurse.getPhoneNumber());
        existingNurse.setAddress(nurse.getAddress());
        existingNurse.setSpecialization(nurse.getSpecialization()); 
        existingNurse.setMedicalLicenseNumber(nurse.getMedicalLicenseNumber());
        existingNurse.setYearsOfExperience(nurse.getYearsOfExperience());
        existingNurse.setPassword(nurse.getPassword());
        existingNurse.setAvailableDays(nurse.getAvailableDays());
        nurseRepository.save(existingNurse); 

        User user = userRepository.findById(existingNurse.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update user fields
        user.setUsername(nurse.getUsername());
        user.setEmail(nurse.getEmail());
        if (nurse.getPassword() != null && !nurse.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(nurse.getPassword())); // Update password
        }
        userRepository.save(user);

        return existingNurse; 
    } 

    @Override
    public void deleteNurse(String userId) {
        Nurse nurse = nurseRepository.findById(userId).orElseThrow(()-> new RuntimeException());
        userRepository.deleteById(nurse.getUserId());
        nurseRepository.deleteById(userId);
    }
}
