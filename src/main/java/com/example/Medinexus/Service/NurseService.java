package com.example.Medinexus.Service;

import java.util.List;

import com.example.Medinexus.Model.Nurse;

public interface NurseService {
    Nurse saveNurse(Nurse nurse); 
    List<Nurse> getAllNurses(); 
    Nurse getNurseById(String id); 
    Nurse updateNurse(Nurse nurse, String id); 
    void deleteNurse(String id);
}
