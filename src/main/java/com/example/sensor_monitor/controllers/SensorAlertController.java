package com.example.sensor_monitor.controllers;

import com.example.sensor_monitor.dtos.SensorDataDTO;
import com.example.sensor_monitor.entities.SensorAlert;
import com.example.sensor_monitor.services.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sensor")
public class SensorAlertController {
    @Autowired
    private SensorDataService sensorDataService;

    @PostMapping
    public SensorAlert save(@RequestBody SensorDataDTO user) {
        return sensorDataService.verifyAndSaveAlert(user);
    }
}
