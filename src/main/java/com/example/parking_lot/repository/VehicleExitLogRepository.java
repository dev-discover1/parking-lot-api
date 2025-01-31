package com.example.parking_lot.repository;

import com.example.parking_lot.model.VehicleExitLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleExitLogRepository extends JpaRepository<VehicleExitLog, Long> {
}
