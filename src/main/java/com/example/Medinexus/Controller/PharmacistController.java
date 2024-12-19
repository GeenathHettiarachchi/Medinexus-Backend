package com.example.Medinexus.Controller;

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

import com.example.Medinexus.Model.Pharmacist;
import com.example.Medinexus.Service.PharmacistService;

@RestController
@RequestMapping("/api/pharmacists")
public class PharmacistController {
    @Autowired
    private PharmacistService pharmacistService;

    @PostMapping
    public ResponseEntity<Pharmacist> savePharmacist(@RequestBody Pharmacist pharmacist){ 
        return new ResponseEntity<>(pharmacistService.savePharmacist(pharmacist), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Pharmacist> getAllPharmacists() {
        return pharmacistService.getAllPharmacists();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pharmacist> getPharmacistById(@PathVariable String id) {
        return new ResponseEntity<>(pharmacistService.getPharmacistById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pharmacist> updatePharmacist(@PathVariable String id, @RequestBody Pharmacist pharmacist) {
        return new ResponseEntity<>(pharmacistService.updatePharmacist(pharmacist, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePharmacist(@PathVariable String id) {
        pharmacistService.deletePharmacist(id);
        return new ResponseEntity<>("Pharmacist deleted successfully", HttpStatus.OK);
    }
}