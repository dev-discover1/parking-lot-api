package com.example.parking_lot.service;

import com.example.parking_lot.model.Admin;
import com.example.parking_lot.repository.AdminRepository;
import com.example.parking_lot.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String registerAdmin(String username, String password) {
        if (adminRepository.findByUsername(username) != null) {
            return "❌ Admin already exists!";
        }

        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(passwordEncoder.encode(password));
        adminRepository.save(admin);

        return "✅ Admin registered successfully!";
    }

    public String loginAdmin(String username, String password) {
        Admin admin = adminRepository.findByUsername(username);
        if (admin == null || !passwordEncoder.matches(password, admin.getPassword())) {
            return "❌ Invalid credentials!";
        }

        return JwtUtil.generateToken(username);
    }
}
