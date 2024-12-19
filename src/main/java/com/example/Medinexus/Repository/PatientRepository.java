package com.example.Medinexus.Repository;

import com.example.Medinexus.Model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientRepository extends MongoRepository<Patient, String> {
}
