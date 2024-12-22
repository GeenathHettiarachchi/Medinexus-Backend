package com.example.Medinexus.Repository;

import com.example.Medinexus.Model.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppointmentRepository extends MongoRepository<Appointment, String> {
}
