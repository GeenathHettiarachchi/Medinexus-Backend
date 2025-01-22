package com.example.Medinexus.Repository;

import com.example.Medinexus.Model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<Admin, String> {
}
