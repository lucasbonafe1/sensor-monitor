package com.example.sensor_monitor.dtos.weather;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForecastDayDTO {
    public LocalDate date;
    public DayDTO day;
}