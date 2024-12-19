package com.example.Medinexus.Repository;

import com.example.Medinexus.Model.Nurse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NurseRepository extends MongoRepository<Nurse, String> {
}
