package com.example.sensor_monitor.services;

import com.example.sensor_monitor.dtos.CurrentDTO;
import com.example.sensor_monitor.dtos.SensorDataDTO;
import com.example.sensor_monitor.dtos.WeatherReturnDTO;
import com.example.sensor_monitor.entities.SensorAlert;
import com.example.sensor_monitor.repositories.SensorDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SensorDataService {
    @Autowired
    public WeatherApiExternalService weatherApiExternalService;

    @Autowired
    public SensorDataRepository sensorDataRepository;

    @Autowired
    public ObjectMapper mapper;

    public void verifyAndSaveAlert(SensorDataDTO sensorDataDTO){
        var sensorExistent = sensorDataRepository.findAll().
                stream().filter(f -> Objects.equals(f.state, sensorDataDTO.state))
                .findFirst().orElse(null);

        if (verifyIfTemperatureIsOverLimit(sensorDataDTO)){
            if (sensorExistent != null){
                sensorExistent.humidityLimit = sensorDataDTO.humidityLimit;
                sensorExistent.temperatureLimit = sensorDataDTO.temperatureLimit;

                sensorDataRepository.save(sensorExistent);
            } else {
                SensorAlert sensorData = mapper.convertValue(sensorDataDTO, SensorAlert.class);

                sensorDataRepository.save(sensorData);
            }
        }
    }

    protected boolean verifyIfTemperatureIsOverLimit(SensorDataDTO sensorData)
    {
        WeatherReturnDTO response = weatherApiExternalService.findByLocation(sensorData.state);
        CurrentDTO currentTemp = response.getCurrent();

        return currentTemp.temp_c > sensorData.temperatureLimit || currentTemp.humidity > sensorData.humidityLimit;
    }
}
