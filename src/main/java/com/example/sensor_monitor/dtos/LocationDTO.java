package com.example.sensor_monitor.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LocationDTO {
    public String name;
    public String region;
    public String country;
    public double lat;
    public double lon;
    public String tz_id;
    public long localtime_epoch;
    public String localtime;
}
