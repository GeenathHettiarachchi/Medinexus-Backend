package com.example.Medinexus.Repository;

import com.example.Medinexus.Model.Medication;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MedicationRepository extends MongoRepository<Medication, String> {
    
}
