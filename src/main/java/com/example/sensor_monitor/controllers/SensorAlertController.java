package com.example.sensor_monitor.controllers;

import com.example.sensor_monitor.entities.SensorAlert;
import com.example.sensor_monitor.services.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensor")
@CrossOrigin(origins = "*")
public class SensorAlertController {
    @Autowired
    private SensorDataService sensorDataService;

    @PostMapping
    public ResponseEntity<?> save(@RequestParam String state, @RequestParam Integer days) {
        SensorAlert sensorCreated = sensorDataService.verifyAndSaveAlert(state, days);

        if (sensorCreated == null)
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Alerta para este estado já existe.");

        return ResponseEntity.status(HttpStatus.CREATED).body(sensorCreated);
    }

    @GetMapping
    public ResponseEntity<List<SensorAlert>> findAll() {
        List<SensorAlert> alerts = sensorDataService.findAll();

        if (alerts.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(alerts);
    }

    @GetMapping("/{state}")
    public ResponseEntity<?> findByState(@PathVariable String state) {
        SensorAlert alert = sensorDataService.findByState(state);

        if (alert == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alerta não encontrado para o estado: " + state);

        return ResponseEntity.ok(alert);
    }
}
