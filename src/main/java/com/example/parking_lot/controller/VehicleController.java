package com.example.parking_lot.controller;

import com.example.parking_lot.model.Vehicle;
import com.example.parking_lot.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/{vehicleNumber}")
    public ResponseEntity<String> getVehicle(@PathVariable String vehicleNumber) {
        Vehicle vehicle = vehicleService.getVehicle(vehicleNumber);
        if (vehicle == null) {
            String res = "Vehicle " + vehicleNumber + " is not currently parked.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
        String res = "Vehicle " + vehicleNumber + " is parked at " + vehicle.getSlot().getSlotNumber();
        return ResponseEntity.ok(res);
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @PostMapping("/entry")
    public ResponseEntity<String> addVehicle(@RequestParam String vehicleNumber) {
        return ResponseEntity.ok(vehicleService.addVehicle(vehicleNumber));
    }

    @PostMapping("/exit")
    public ResponseEntity<String> exitVehicle(@RequestParam String vehicleNumber) {
        return ResponseEntity.ok(vehicleService.exitVehicle(vehicleNumber));
    }
}
