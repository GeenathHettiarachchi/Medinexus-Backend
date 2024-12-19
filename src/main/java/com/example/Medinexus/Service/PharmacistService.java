package com.example.Medinexus.Service;

import java.util.List;
import com.example.Medinexus.Model.Pharmacist;

public interface PharmacistService {
    Pharmacist savePharmacist(Pharmacist pharmacist); 
    List<Pharmacist> getAllPharmacists(); 
    Pharmacist getPharmacistById(String id); 
    Pharmacist updatePharmacist(Pharmacist pharmacist, String id); 
    void deletePharmacist(String id);
}