package com.example.Medinexus.Service.Impl;

// import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.Medinexus.Model.Doctor;
import com.example.Medinexus.Model.User;
import com.example.Medinexus.Repository.DoctorRepository;
import com.example.Medinexus.Repository.UserRepository;
import com.example.Medinexus.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // private static final List<String> ALLOWED_FILE_TYPES = Arrays.asList("pdf", "jpg", "jpeg", "png");
    // private static final String MEDICAL_CERTIFICATION_UPLOAD_DIR = "uploads/medical_certifications/";
    // private static final String PROFILE_PICTURE_UPLOAD_DIR = "uploads/profile_pictures/";

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getDoctorById(String userId) {
        Optional<Doctor> doctor =  doctorRepository.findById(userId);
        if(doctor.isPresent()){
            return doctor.get();
        }else {
            throw new RuntimeException("Doctor not found");
        }
    }

    @Override
    public Doctor updateDoctor(Doctor doctor, String userId) {
        // update doctor fields
        Doctor existingDoctor = doctorRepository.findById(userId).orElseThrow(()-> new RuntimeException());
        existingDoctor.setFullName(doctor.getFullName());
        existingDoctor.setGender(doctor.getGender());
        existingDoctor.setDateOfBirth(doctor.getDateOfBirth());
        existingDoctor.setSpecialization(doctor.getSpecialization());
        existingDoctor.setMedicalLicenseNumber(doctor.getMedicalLicenseNumber());
        existingDoctor.setYearsOfExperience(doctor.getYearsOfExperience());
        existingDoctor.setEmail(doctor.getEmail());
        existingDoctor.setPhoneNumber(doctor.getPhoneNumber());
        existingDoctor.setClinicAddress(doctor.getClinicAddress());
        existingDoctor.setUsername(doctor.getUsername());
        existingDoctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
        existingDoctor.setAvailableDays(doctor.getAvailableDays());
        doctorRepository.save(existingDoctor);

        User user = userRepository.findById(existingDoctor.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update user fields
        user.setUsername(doctor.getUsername());
        user.setEmail(doctor.getEmail());
        if (doctor.getPassword() != null && !doctor.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(doctor.getPassword())); // Update password
        }
        userRepository.save(user);

        return existingDoctor;
    }

    @Override
    public void deleteDoctor(String userId) {
        Doctor doctor = doctorRepository.findById(userId).orElseThrow(()-> new RuntimeException());
        userRepository.deleteById(doctor.getUserId());
        doctorRepository.deleteById(userId);
    }
}