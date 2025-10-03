package com.example.sensor_monitor.services;

import com.example.sensor_monitor.dtos.CurrentDTO;
import com.example.sensor_monitor.dtos.SensorDataDTO;
import com.example.sensor_monitor.dtos.WeatherReturnDTO;
import com.example.sensor_monitor.entities.SensorAlert;
import com.example.sensor_monitor.repositories.SensorDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;

@Service
public class SensorDataService {
    @Autowired
    public WeatherApiExternalService weatherApiExternalService;

    @Autowired
    public SensorDataRepository sensorDataRepository;

    @Autowired
    public KafkaProducerService kafkaProducerService;

    public SensorAlert verifyAndSaveAlert(SensorDataDTO dto){
        SensorAlert savedAlert = new SensorAlert();
        savedAlert.setState(dto.state);

        try {
            SensorAlert sensorExistent = sensorDataRepository.findFirstByState(dto.state);
            CurrentDTO temperatureResponse = verifyIfTemperatureIsOverLimit(dto);

            if (temperatureResponse != null){
                savedAlert.setCurrentTemperature(temperatureResponse.temp_c, dto.temperatureLimit,
                            temperatureResponse.humidity, dto.humidityLimit);

                if (sensorExistent != null)
                    savedAlert.setId(sensorExistent.id);

                sensorDataRepository.save(savedAlert);
                kafkaProducerService.sendMessageOrder(dto);
            }
            System.out.println("Alerta processado para a localidade: )" + dto.getState());
        } catch (Exception e){
            System.out.println("Erro ao salvar/atualizar alerta para a localidade: " + dto.getState() +"\n"+ e);
        }

        return savedAlert;
    }

    protected CurrentDTO verifyIfTemperatureIsOverLimit(SensorDataDTO sensorData)
    {
        WeatherReturnDTO response = weatherApiExternalService.findByLocation(sensorData.state);
        CurrentDTO currentTemp = response.getCurrent();

        if(currentTemp != null && (currentTemp.temp_c > sensorData.temperatureLimit || currentTemp.humidity > sensorData.humidityLimit))
            return currentTemp;

        return null;
    }
}
