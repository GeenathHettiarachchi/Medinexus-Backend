package com.example.Medinexus.Service;

import java.util.List;
import com.example.Medinexus.Model.Doctor;

public interface DoctorService {
    Doctor saveDoctor(Doctor doctor);
    List<Doctor> getAllDoctors();
    Doctor getDoctorById(String id);
    Doctor updateDoctor(Doctor doctor, String id);
    void deleteDoctor(String id);
}