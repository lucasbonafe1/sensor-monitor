package com.example.sensor_monitor.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurrentDTO {
    public Double temp_c;
    public ConditionDTO condition;
    public Double wind_mph;
    public Double humidity;
    public Double feelslike_c;
    public Double vis_km;
    public Double vis_miles;
    public Double gust_mph;
    public Double gust_kph;
}