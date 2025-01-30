package com.example.parking_lot.controller;


import com.example.parking_lot.model.Slot;
import com.example.parking_lot.service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/slots")
public class SlotController {

    @Autowired
    private SlotService slotService;

    @GetMapping
    public List<Slot> getAllSlots(){
        return slotService.getAllSlots();
    }

    @PostMapping("/add")
    public String addSlot(@RequestParam String slotNumber){
        slotService.addSlot(slotNumber);
        return "✅ Slot " + slotNumber + " added successfully!";
    }

    @PostMapping("/occupy")
    public String occupySlot(@RequestParam String slotNumber) {
        boolean occupied = slotService.occupySlot(slotNumber);
        return occupied ? "✅ Slot " + slotNumber + " is now occupied!" : "❌ Slot is already occupied or not found!";
    }

    @PostMapping("/free")
    public String freeSlot(@RequestParam String slotNumber) {
        boolean freed = slotService.freeSlot(slotNumber);
        return freed ? "✅ Slot " + slotNumber + " is now free!" : "❌ Slot is already free or not found!";
    }

    @PostMapping("/delete")
    public String deleteSlot(@RequestParam String slotNumber) {
        boolean deleted = slotService.deleteSlot(slotNumber);
        return deleted ? "✅ Slot " + slotNumber + " has been deleted!" : "❌ Slot is occupied or not found!";
    }
}
