package com.example.Medinexus.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Medinexus.Model.Appointment;
import com.example.Medinexus.Repository.AppointmentRepository;
import com.example.Medinexus.Service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository; 
 
    @Override 
    public Appointment saveAppointment(Appointment appointment) { 
        return appointmentRepository.save(appointment); 
    }

    @Override 
    public List<Appointment> getAllAppointments() { 
        return appointmentRepository.findAll(); 
    } 

    @Override 
    public Appointment getAppointmentById(String id) { 
        Optional<Appointment> appointment =  appointmentRepository.findById(id); 
        if(appointment.isPresent()){ 
            return appointment.get(); 
        }else { 
            throw new RuntimeException("Appointment not found"); 
        } 
    } 
 
    @Override 
    public Appointment updateAppointment(Appointment appointment, String id) { 
        Appointment existingAppointment = appointmentRepository.findById(id).orElseThrow(()-> new RuntimeException()); 
        existingAppointment.setDoctorId(appointment.getDoctorId()); 
        existingAppointment.setNurseId(appointment.getNurseId());
        existingAppointment.setPatientId(appointment.getPatientId());
        appointmentRepository.save(existingAppointment); 
        return existingAppointment; 
    } 

    @Override 
    public void deleteAppointment(String id) { 
        appointmentRepository.findById(id).orElseThrow(()-> new RuntimeException("Appointment not found")); 
        appointmentRepository.deleteById(id);
    }
}
