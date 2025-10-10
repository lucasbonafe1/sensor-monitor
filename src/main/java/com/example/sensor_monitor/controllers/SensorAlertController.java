package com.example.sensor_monitor.controllers;

import com.example.sensor_monitor.dtos.SensorDataDTO;
import com.example.sensor_monitor.entities.SensorAlert;
import com.example.sensor_monitor.services.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensor")
public class SensorAlertController {
    @Autowired
    private SensorDataService sensorDataService;

    @PostMapping
    public SensorAlert save(@RequestBody SensorDataDTO user) {
        return sensorDataService.verifyAndSaveAlert(user);
    }

    @GetMapping
    public List<SensorAlert> findAll() {
        return sensorDataService.findAll();
    }

    @GetMapping("/{state}")
    public SensorAlert findByState(@PathVariable String state) {
        return sensorDataService.findByState(state);
    }
}
