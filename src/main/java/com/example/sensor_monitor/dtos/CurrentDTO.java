package com.example.sensor_monitor.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CurrentDTO {
    public double temp_c;
    public ConditionDTO condition;
    public double wind_mph;
    public int humidity;
    public double feelslike_c;
    public double vis_km;
    public double vis_miles;
    public double gust_mph;
    public double gust_kph;
}