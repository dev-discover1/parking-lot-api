package com.example.parking_lot.service;

import com.example.parking_lot.model.VehicleExitLog;
import com.example.parking_lot.repository.VehicleExitLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public long getTotalUsersServedOnDay(LocalDateTime date) {
        LocalDateTime startOfDay = date.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = date.toLocalDate().atTime(23, 59, 59);

        return vehicleExitLogRepository.countByExitTimeBetween(startOfDay, endOfDay);
    }
}
