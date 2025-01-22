package com.example.Medinexus.Controller;

import com.example.Medinexus.Model.Medication;
import com.example.Medinexus.Service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/medications")

public class MedicationController {
    @Autowired
    private MedicationService medicationService;

    @PostMapping
    public ResponseEntity<Medication> saveMedication(@RequestBody Medication medication){ 
        return new ResponseEntity<>(medicationService.saveMedication(medication), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Medication> getAllMedications() {
        return medicationService.getAllMedications();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medication> getMedicationById(@PathVariable String id) {
        return new ResponseEntity<>(medicationService.getMedicationById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medication> updateMedication(@PathVariable String id, @RequestBody Medication medication) {
        return new ResponseEntity<>(medicationService.updateMedication(medication, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMedication(@PathVariable String id) {
        medicationService.deleteMedication(id);
        return new ResponseEntity<>("Medication deleted successfully", HttpStatus.OK);
    }
}
