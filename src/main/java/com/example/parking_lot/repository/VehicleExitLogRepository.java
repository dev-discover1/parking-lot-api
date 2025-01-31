package com.example.parking_lot.repository;

import com.example.parking_lot.model.VehicleExitLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VehicleExitLogRepository extends JpaRepository<VehicleExitLog, Long> {
    long countByExitTimeBetween(LocalDateTime startTime, LocalDateTime endTime);

    List<VehicleExitLog> findByExitTimeBetween(LocalDateTime startTime, LocalDateTime endTime);
}
