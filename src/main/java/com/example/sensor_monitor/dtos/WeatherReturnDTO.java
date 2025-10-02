package com.example.sensor_monitor.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WeatherReturnDTO {
    private LocationDTO location;
    private CurrentDTO current;

    private ConditionDTO conditionDTO;
}

