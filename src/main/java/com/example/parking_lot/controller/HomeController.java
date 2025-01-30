package com.example.parking_lot.controller;

import com.example.parking_lot.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {
    @Autowired
    private DatabaseService databaseService;


    @GetMapping
    public String home(){
        return "Welcome to Parking Lot System!";
    }

    @GetMapping("/test-db")
    public String testDatabaseConnection(){
        if (databaseService.isDatabaseConnected()) {
            return "✅ Database connection is successful!";
        } else {
            return "❌ Database connection failed!";
        }
    }
}
