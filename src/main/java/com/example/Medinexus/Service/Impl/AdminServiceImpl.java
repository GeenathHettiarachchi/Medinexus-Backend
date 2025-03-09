package com.example.Medinexus.Service.Impl;

import java.util.List;
import java.util.Optional;

import com.example.Medinexus.Model.Admin;
import com.example.Medinexus.Model.User;
import com.example.Medinexus.Repository.AdminRepository;
import com.example.Medinexus.Repository.UserRepository;
import com.example.Medinexus.Service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin getAdminById(String id) {
        Optional<Admin> admin =  adminRepository.findById(id);
        if(admin.isPresent()){
            return admin.get();
        }else {
            throw new RuntimeException("Admin not found");
        }
    }

    @Override
    public Admin updateAdmin(Admin admin, String id) {
        Admin existingAdmin = adminRepository.findById(id).orElseThrow(()-> new RuntimeException());
        existingAdmin.setAdminName(admin.getAdminName());
        existingAdmin.setAdminAddress(admin.getAdminAddress());
        existingAdmin.setAdminPhone(admin.getAdminPhone());
        existingAdmin.setEmail(admin.getEmail());
        existingAdmin.setPassword(admin.getPassword());
        adminRepository.save(existingAdmin);

        User user = userRepository.findById(existingAdmin.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update user fields
        user.setUsername(admin.getUsername());
        user.setEmail(admin.getEmail());
        if (admin.getPassword() != null && !admin.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(admin.getPassword())); // Update password
        }
        userRepository.save(user);

        return existingAdmin;
    }

    @Override
    public void deleteAdmin(String id) {
        Admin admin = adminRepository.findById(id).orElseThrow(()-> new RuntimeException());
        userRepository.deleteById(admin.getUserId());
        adminRepository.deleteById(id);
    }
}
