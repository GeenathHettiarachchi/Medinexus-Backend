package com.example.Medinexus.Service.Impl;

import java.util.List;
import java.util.Optional;

import com.example.Medinexus.Model.Admin;
import com.example.Medinexus.Repository.AdminRepository;
import com.example.Medinexus.Service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin saveAdmin(Admin doctor) {
        return adminRepository.save(doctor);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin getAdminById(String id) {
        Optional<Admin> doctor =  adminRepository.findById(id);
        if(doctor.isPresent()){
            return doctor.get();
        }else {
            throw new RuntimeException("Doctor not found");
        }
    }

    @Override
    public Admin updateAdmin(Admin admin, String id) {
        Admin existingAdmin = adminRepository.findById(id).orElseThrow(()-> new RuntimeException());
        existingAdmin.setAdminName(admin.getAdminName());
        existingAdmin.setAdminAddress(admin.getAdminAddress());
        existingAdmin.setAdminPhone(admin.getAdminPhone());
        existingAdmin.setAdminEmail(admin.getAdminEmail());
        existingAdmin.setAdminPassword(admin.getAdminPassword());
        adminRepository.save(existingAdmin);
        return existingAdmin;
    }

    @Override
    public void deleteAdmin(String id) {
        adminRepository.findById(id).orElseThrow(()-> new RuntimeException());
        adminRepository.deleteById(id);
    }
}
