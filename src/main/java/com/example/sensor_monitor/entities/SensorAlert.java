package com.example.sensor_monitor.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "sensorAlert")
public class SensorAlert {
    @Id
    public String id;
    @Indexed(unique = true)
    public String state;
    public Double temperature;
    public Double temperatureLimit;
    public Double humidity;
    public Double humidityLimit;
    public String alertDate;

    public SensorAlert() {
        var date = Date.from(Instant.now());
        this.alertDate = getFormattedDate(date);
    }

    public String getFormattedDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdf.format(date);
    }

    public void setCurrentTemperature(Double currentTemperature, Double currentTemperatureLimit, Double currentHumidity, Double currentHumidityLimit){
        this.temperature = currentTemperature;
        this.temperatureLimit = currentTemperatureLimit;
        this.humidity = currentHumidity;
        this.humidityLimit = currentHumidityLimit;
    };
}
