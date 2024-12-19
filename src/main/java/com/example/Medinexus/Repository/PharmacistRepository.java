package com.example.Medinexus.Repository;

import com.example.Medinexus.Model.Pharmacist;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PharmacistRepository extends MongoRepository<Pharmacist, String> {
}