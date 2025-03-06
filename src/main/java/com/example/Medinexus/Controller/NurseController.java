package com.example.Medinexus.Controller;

import com.example.Medinexus.Model.Nurse;
import com.example.Medinexus.Service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/api/nurses")
@CrossOrigin(origins = "*")
public class NurseController {

    @Autowired
    private NurseService nurseService;

    @PostMapping
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<Nurse> saveNurse(@RequestBody Nurse nurse){ 
        return new ResponseEntity<>(nurseService.saveNurse(nurse), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Nurse> getAllNurses() {
        return nurseService.getAllNurses();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Nurse> getNurseById(@PathVariable String id) {
        return new ResponseEntity<>(nurseService.getNurseById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nurse> updateNurse(@PathVariable String id, @RequestBody Nurse nurse) {
        return new ResponseEntity<>(nurseService.updateNurse(nurse, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNurse(@PathVariable String id) {
        nurseService.deleteNurse(id);
        return new ResponseEntity<>("Nurse deleted successfully", HttpStatus.OK);
    }
}
