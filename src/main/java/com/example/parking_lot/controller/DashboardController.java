package com.example.parking_lot.controller;

import com.example.parking_lot.model.VehicleExitLog;
import com.example.parking_lot.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DashboardController {

    private final DashboardService dashboardService;

    @Autowired
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard/exit-logs")
    public List<VehicleExitLog> getExitLogs() {
        return dashboardService.getAllExitLogs();
    }
}
