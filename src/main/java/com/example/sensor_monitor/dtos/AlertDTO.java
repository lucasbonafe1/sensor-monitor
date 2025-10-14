package com.example.sensor_monitor.dtos;

import com.example.sensor_monitor.dtos.weather.ForecastDayDTO;
import com.example.sensor_monitor.enums.AlertTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlertDTO {
    public String state;
    public Double temperature;
    public Double humidity;
    public Double wind;
    public Double precipitation;
    public AlertTypeEnum alertType;
    public ForecastDayDTO forecastDay;

    public AlertDTO(String state, Double temperature, Double humidity, Double wind, Double precipitation, ForecastDayDTO forecastDay) {
        this.state = state;
        this.temperature = temperature;
        this.humidity = humidity;
        this.wind = wind;
        this.precipitation = precipitation;
        this.forecastDay = forecastDay;
    }
}
