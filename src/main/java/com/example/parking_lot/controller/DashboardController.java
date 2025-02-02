package com.example.parking_lot.controller;

import com.example.parking_lot.model.VehicleExitLog;
import com.example.parking_lot.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    @Autowired
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/logs/exits")
    public List<VehicleExitLog> getExitLogs() {
        return dashboardService.getAllExitLogs();
    }

    @GetMapping("/users/served")
    public String getTotalUsersServed(@RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        long totalUsers = dashboardService.getTotalUsersServedOnDay(localDate);
        return "Total users served on " + date + ": " + totalUsers;
    }

    @GetMapping("/users/served/all")
    public String getTotalUsersServedAllTime() {
        long totalUsers = dashboardService.getTotalUsersServed();
        return "Total users served till now: " + totalUsers;
    }

    @GetMapping("/revenue")
    public String getTotalRevenue(@RequestParam String startDate, @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
        double totalRevenue = dashboardService.getTotalRevenueInDateRange(start, end);
        return "Total revenue from " + startDate + " to " + endDate + ": ₹" + totalRevenue;
    }

    @GetMapping("/revenue/all")
    public String getTotalRevenueAllTime() {
        double totalRevenue = dashboardService.getTotalRevenue();
        return "Total revenue till now: ₹" + totalRevenue;
    }
}
