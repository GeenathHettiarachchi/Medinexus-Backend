package com.example.Medinexus.Repository;

import com.example.Medinexus.Model.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DoctorRepository extends MongoRepository<Doctor, String> {
}