package com.example.sensor_monitor.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CurrentDTO {
    private double temp_c;
    private ConditionDTO condition;
    private double wind_mph;
    private int humidity;
    private double feelslike_c;
    private double vis_km;
    private double vis_miles;
    private double gust_mph;
    private double gust_kph;
}