package com.example.sensor_monitor.dtos.weather;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherReturnDTO {
    public LocationDTO location;
    public CurrentDTO current;
    public ForecastDTO forecast;
}

