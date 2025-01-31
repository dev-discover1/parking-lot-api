package com.example.parking_lot.service;

import com.example.parking_lot.model.VehicleExitLog;
import com.example.parking_lot.repository.VehicleExitLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    private final VehicleExitLogRepository vehicleExitLogRepository;

    public DashboardService(VehicleExitLogRepository vehicleExitLogRepository) {
        this.vehicleExitLogRepository = vehicleExitLogRepository;
    }

    public List<VehicleExitLog> getAllExitLogs() {
        return vehicleExitLogRepository.findAll();
    }
}
