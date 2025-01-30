package com.example.parking_lot.repository;

import com.example.parking_lot.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {
    Slot findBySlotNumber(String slotNumber);
}
