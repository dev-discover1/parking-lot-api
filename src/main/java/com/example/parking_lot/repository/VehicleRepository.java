package com.example.parking_lot.repository;

import com.example.parking_lot.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Vehicle findByVehicleNumberAndIsActiveTrue(String vehicleNumber);
}
