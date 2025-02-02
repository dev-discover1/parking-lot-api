package com.example.parking_lot.service;


import com.example.parking_lot.model.Slot;
import com.example.parking_lot.model.Vehicle;
import com.example.parking_lot.model.VehicleExitLog;
import com.example.parking_lot.repository.VehicleExitLogRepository;
import com.example.parking_lot.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final SlotService slotService;
    private final VehicleExitLogRepository vehicleExitLogRepository;

    public VehicleService(VehicleRepository vehicleRepository, SlotService slotService, VehicleExitLogRepository vehicleExitLogRepository) {
        this.vehicleRepository = vehicleRepository;
        this.slotService = slotService;
        this.vehicleExitLogRepository = vehicleExitLogRepository;
    }

    public Vehicle getVehicle(String vehicleNumber) {
        return vehicleRepository.findByVehicleNumberAndIsActiveTrue(vehicleNumber);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public String addVehicle(String vehicleNumber) {
        if (vehicleRepository.findByVehicleNumberAndIsActiveTrue(vehicleNumber) != null) {
            return "Vehicle " + vehicleNumber + " is already parked!";
        }

        Slot availableSlot = slotService.getFirstAvailableSlot();
        if (availableSlot == null) {
            return "No empty slots available for vehicle " + vehicleNumber;
        }

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNumber(vehicleNumber);
        vehicle.setEntryTime(LocalDateTime.now());
        vehicle.setIsActive(true);
        vehicle.setSlot(availableSlot);
        vehicleRepository.save(vehicle);

        slotService.occupySlot(String.valueOf(availableSlot.getSlotNumber()));

        return "Vehicle " + vehicleNumber + " added successfully to slot " + availableSlot.getSlotNumber() + "!";
    }

    public String exitVehicle(String vehicleNumber) {
        Vehicle vehicle = vehicleRepository.findByVehicleNumberAndIsActiveTrue(vehicleNumber);
        if (vehicle == null) {
            return "Vehicle " + vehicleNumber + " is not currently parked.";
        }

        LocalDateTime entryTime = vehicle.getEntryTime();
        LocalDateTime exitTime = LocalDateTime.now();
        long totalHours = java.time.Duration.between(entryTime, exitTime).toHours() + 1;

        // Calculate total charge
        double totalCharge = (totalHours <= 3) ? totalHours * 100.0 : (3 * 100.0) + ((totalHours - 3) * 150.0);

        vehicle.setIsActive(false);
        vehicleRepository.save(vehicle);

        slotService.freeSlot(String.valueOf(String.valueOf(vehicle.getSlot().getSlotNumber())));

        vehicleRepository.delete(vehicle);

        // Save vehicle exit log
        VehicleExitLog exitLog = new VehicleExitLog();
        exitLog.setVehicleNumber(vehicleNumber);
        exitLog.setInTime(entryTime);
        exitLog.setExitTime(exitTime);
        exitLog.setTotalHours((int) totalHours);
        exitLog.setTotalCharge(totalCharge);
        vehicleExitLogRepository.save(exitLog);

        return "Vehicle " + vehicleNumber + " exited. Total Hours: " + totalHours + ", Charge: ₹" + totalCharge;
    }
}
