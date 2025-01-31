package com.example.parking_lot.service;

import com.example.parking_lot.model.Slot;
import com.example.parking_lot.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlotService {

    @Autowired
    private SlotRepository slotRepository;

    public List<Slot> getAllSlots(){
        return slotRepository.findAll();
    }

    public Slot addSlot(String slotNumber) {
        Slot slot = new Slot();
        slot.setSlotNumber(slotNumber);
        slot.setOccupied(false);
        return slotRepository.save(slot);
    }

    public boolean occupySlot(String slotNumber) {
        Slot slot = slotRepository.findBySlotNumber(slotNumber);
        if (slot != null && !slot.isOccupied()) {
            slot.setOccupied(true);
            slotRepository.save(slot);
            return true;
        }
        return false;
    }

    public boolean freeSlot(String slotNumber) {
        Slot slot = slotRepository.findBySlotNumber(slotNumber);
        if (slot != null && slot.isOccupied()) {
            slot.setOccupied(false);
            slotRepository.save(slot);
            return true;
        }
        return false;
    }

    public boolean deleteSlot(String slotNumber) {
        Slot slot = slotRepository.findBySlotNumber(slotNumber);
        if (slot != null) {
            if (!slot.isOccupied()) {
                slotRepository.delete(slot);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public Slot getFirstAvailableSlot() {
        List<Slot> availableSlots = slotRepository.findAll();
        for (Slot slot : availableSlots) {
            if (!slot.isOccupied()) {
                return slot;
            }
        }
        return null;
    }
}
