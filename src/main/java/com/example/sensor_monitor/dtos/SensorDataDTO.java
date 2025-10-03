package com.example.sensor_monitor.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SensorDataDTO {
    public String state;
    public Double temperatureLimit;
    public Double humidityLimit;
}

