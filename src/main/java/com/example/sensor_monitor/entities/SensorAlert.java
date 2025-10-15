package com.example.sensor_monitor.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Getter
@AllArgsConstructor
@Document(collection = "sensorAlert")
public class SensorAlert {
    @Id
    public String id;
    public String state;
    @Indexed(unique = true)
    public String normalizedState;
    public Double temperature;
    public Double maxTemperature;
    public Double minTemperature;
    public Double humidity;
    public Long maxHumidity;
    public Double preciptation;
    public Long maxPreciptation;
    public Double wind;
    public Double maxWind;
    public String alertDate;

    public SensorAlert() {
        var date = Date.from(Instant.now());
        this.alertDate = getFormattedDate(date);
    }

    public String getFormattedDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdf.format(date);
    }

    public void setCurrentTemperature(Double currentTemperature, Double maxTemperature, Double minTemperature){
        this.temperature = currentTemperature;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
    };

    public void setHumidity(Double humidity, Long maxHumidity){
        this.humidity = humidity;
        this.maxHumidity = maxHumidity;
    }

    public void setPrecipitation(Double preciptation, Long maxPreciptation) {
        this.preciptation = preciptation;
        this.maxPreciptation = maxPreciptation;
    }

    public void setWind(Double wind, Double maxWind) {
        this.wind = wind;
        this.maxWind = maxWind;
    }

    public void setState(String state) {
        this.state = state;
    }
}
