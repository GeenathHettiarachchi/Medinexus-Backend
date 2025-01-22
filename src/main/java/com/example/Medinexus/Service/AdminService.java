package com.example.Medinexus.Service;

import java.util.List;
import com.example.Medinexus.Model.Admin;

public interface AdminService {
    Admin saveAdmin(Admin admin);
    List<Admin> getAllAdmins();
    Admin getAdminById(String id);
    Admin updateAdmin(Admin admin, String id);
    void deleteAdmin(String id);
}
