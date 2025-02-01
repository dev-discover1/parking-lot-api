package com.example.parking_lot.service;

import com.example.parking_lot.model.Admin;
import com.example.parking_lot.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Admin registerAdmin(String username, String password) {
        if (adminRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException("Username is already taken.");
        }

        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(bCryptPasswordEncoder.encode(password));

        return adminRepository.save(admin);
    }

    public Admin findAdminByUsername(String username) {
        Optional<Admin> adminOptional = Optional.ofNullable(adminRepository.findByUsername(username));
        if (adminOptional.isEmpty()) {
            throw new UsernameNotFoundException("Admin not found: " + username);
        }
        return adminOptional.get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new AdminUserDetails(findAdminByUsername(username)); // Create custom UserDetails from Admin entity
    }
}