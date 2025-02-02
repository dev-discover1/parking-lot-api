package com.example.parking_lot.controller;

import com.example.parking_lot.model.Admin;
import com.example.parking_lot.service.AuthService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/admins")
    public List<Admin> getAllAdmins() {
        return authService.getAllAdmins();
    }

    @PostMapping("/register")
    public String register(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        return authService.registerAdmin(username, password);
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        return authService.loginAdmin(username, password);
    }
}