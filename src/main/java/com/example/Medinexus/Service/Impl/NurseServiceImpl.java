package com.example.Medinexus.Service.Impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.Medinexus.Model.Nurse;
import com.example.Medinexus.Repository.NurseRepository;
import com.example.Medinexus.Service.NurseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class NurseServiceImpl implements NurseService {
    @Autowired 
    private NurseRepository nurseRepository; 
 
    private static final String UPLOAD_DIR = "uploads/medical-certifications/";

    @Override
    public Nurse saveNurse(Nurse nurse, MultipartFile medicalCertificationFile) {
        try {
            // Save the file to the upload directory
            String filePath = saveFile(medicalCertificationFile);
            nurse.setMedicalCertificationFilePath(filePath);
            return nurseRepository.save(nurse);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save medical certification file", e);
        }
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
        existingNurse.setPassword(nurse.getPassword());
        existingNurse.setAvailableDays(nurse.getAvailableDays());
        nurseRepository.save(existingNurse); 
        return existingNurse; 
    } 

    @Override 
    public void deleteNurse(String id) { 
        nurseRepository.findById(id).orElseThrow(()-> new RuntimeException()); 
        nurseRepository.deleteById(id); 
    }

    private String saveFile(MultipartFile file) throws IOException {
        // Ensure the upload directory exists
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Generate a unique file name
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);

        // Save the file
        Files.copy(file.getInputStream(), filePath);

        return filePath.toString();
    }
}
