package com.example.sensor_monitor.dtos.weather;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DayDTO {
    public Double maxtempC;
    public Double mintempC;
    public Double maxwindMph;
    public Long totalprecipMm;
    public Long avghumidity;
}
