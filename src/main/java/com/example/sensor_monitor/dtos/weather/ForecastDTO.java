package com.example.sensor_monitor.dtos.weather;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForecastDTO {
    public List<ForecastDayDTO> forecastDay;
}