package com.example.sensor_monitor.dtos.weather;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationDTO {
    public String name;
    public String region;
    public String country;
    public Double lat;
    public Double lon;
    public String tz_id;
    public Long localtime_epoch;
    public String localtime;
}
