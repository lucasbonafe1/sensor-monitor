package com.example.sensor_monitor.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SensorData {
    public Integer id;
    public String state;
    public Double temperature;
    public Double temperatureLimit;
    public Double umidade;
    public Double umidadeLimit;
}
