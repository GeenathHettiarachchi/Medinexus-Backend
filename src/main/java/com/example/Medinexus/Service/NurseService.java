package com.example.Medinexus.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.Medinexus.Model.Nurse;

public interface NurseService {
    Nurse saveNurse(Nurse nurse, MultipartFile medicalCertificationFile); 
    List<Nurse> getAllNurses(); 
    Nurse getNurseById(String id); 
    Nurse updateNurse(Nurse nurse, String id); 
    void deleteNurse(String id);
}
