package com.example.sensor_monitor.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SensorDataDTO {
    public String state;
    public Double temperatureLimit;
    public Double humidityLimit;
    public String timestamp;
}

