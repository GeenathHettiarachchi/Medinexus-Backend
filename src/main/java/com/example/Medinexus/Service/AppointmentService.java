package com.example.Medinexus.Service;

import java.util.List;

import com.example.Medinexus.Model.Appointment;

public interface AppointmentService {
    Appointment saveAppointment(Appointment appointment); 
    List<Appointment> getAllAppointments(); 
    Appointment getAppointmentById(String id); 
    Appointment updateAppointment(Appointment appointment, String id); 
    void deleteAppointment(String id);
}
