package com.example.parking_lot.service;

import com.example.parking_lot.model.VehicleExitLog;
import com.example.parking_lot.repository.VehicleExitLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public long getTotalUsersServedOnDay(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);

        return vehicleExitLogRepository.countByExitTimeBetween(startOfDay, endOfDay);
    }

    public double getTotalRevenueInDateRange(LocalDate startDate, LocalDate endDate) {
        LocalDateTime startOfRange = startDate.atStartOfDay();
        LocalDateTime endOfRange = endDate.atTime(23, 59, 59);

        List<VehicleExitLog> exitLogs = vehicleExitLogRepository.findByExitTimeBetween(startOfRange, endOfRange);
        return exitLogs.stream().mapToDouble(VehicleExitLog::getTotalCharge).sum();
    }

    public long getTotalUsersServed() {
        return vehicleExitLogRepository.count();
    }

    public double getTotalRevenue() {
        return vehicleExitLogRepository.findAll()
                .stream()
                .mapToDouble(VehicleExitLog::getTotalCharge)
                .sum();
    }
}
