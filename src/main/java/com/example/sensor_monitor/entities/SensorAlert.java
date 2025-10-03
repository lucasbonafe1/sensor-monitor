package com.example.sensor_monitor.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "sensorAlert")
public class SensorAlert {
    @Id
    public String id;
    public String state;
    public Double temperature;
    public Double temperatureLimit;
    public Double humidity;
    public Double humidityLimit;
    public LocalDateTime alertDate;
}
