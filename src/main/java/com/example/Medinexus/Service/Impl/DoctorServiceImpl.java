package com.example.Medinexus.Service.Impl;

import java.util.List;
import java.util.Optional;

import com.example.Medinexus.Model.Doctor;
import com.example.Medinexus.Repository.DoctorRepository;
import com.example.Medinexus.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getDoctorById(String id) {
        Optional<Doctor> doctor =  doctorRepository.findById(id);
        if(doctor.isPresent()){
            return doctor.get();
        }else {
            throw new RuntimeException("Doctor not found");
        }
    }

    @Override
    public Doctor updateDoctor(Doctor doctor, String id) {
        Doctor existingDoctor = doctorRepository.findById(id).orElseThrow(()-> new RuntimeException());
        existingDoctor.setFullName(doctor.getFullName());
        existingDoctor.setGender(doctor.getGender());
        existingDoctor.setDateOfBirth(doctor.getDateOfBirth());
        existingDoctor.setSpecialization(doctor.getSpecialization());
        existingDoctor.setMedicalLicenseNumber(doctor.getMedicalLicenseNumber());
        existingDoctor.setYearsOfExperience(doctor.getYearsOfExperience());
        existingDoctor.setEmail(doctor.getEmail());
        existingDoctor.setPhoneNumber(doctor.getPhoneNumber());
        existingDoctor.setClinicAddress(doctor.getClinicAddress());
        existingDoctor.setPassword(doctor.getPassword());
        existingDoctor.setAvailableDays(doctor.getAvailableDays());
        doctorRepository.save(existingDoctor);
        return existingDoctor;
    }

    @Override
    public void deleteDoctor(String id) {
        doctorRepository.findById(id).orElseThrow(()-> new RuntimeException());
        doctorRepository.deleteById(id);
    }
}