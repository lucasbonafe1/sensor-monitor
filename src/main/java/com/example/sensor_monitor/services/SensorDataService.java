package com.example.sensor_monitor.services;

import com.example.sensor_monitor.dtos.AlertDTO;
import com.example.sensor_monitor.dtos.weather.*;
import com.example.sensor_monitor.entities.SensorAlert;
import com.example.sensor_monitor.enums.AlertTypeEnum;
import com.example.sensor_monitor.repositories.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SensorDataService {
    @Autowired
    public WeatherApiExternalService weatherApiExternalService;

    @Autowired
    public SensorDataRepository sensorDataRepository;

    @Autowired
    public KafkaProducerService kafkaProducerService;

    public SensorAlert verifyAndSaveAlert(String state, Integer days){
        SensorAlert savedAlert = new SensorAlert();
        savedAlert.normalizedState = state.toLowerCase().trim();

        try {
            SensorAlert sensorExistent = sensorDataRepository.findFirstByNormalizedState(savedAlert.normalizedState);
            List<AlertDTO> alertDTOS = verifyIfTemperatureIsOverLimit(savedAlert.normalizedState, days);

            for (AlertDTO alert : alertDTOS){
                savedAlert.setState(alert.state);

                savedAlert.setCurrentTemperature(
                        alert.temperature,
                        alert.forecastDay.day.maxtempC,
                        alert.forecastDay.day.mintempC
                );

                savedAlert.setHumidity(
                        alert.humidity,
                        alert.forecastDay.day.avghumidity.longValue()
                );

                savedAlert.setPrecipitation(
                        alert.precipitation,
                        alert.forecastDay.day.totalprecipMm
                );

                savedAlert.setWind(
                        alert.wind,
                        alert.forecastDay.day.maxwindMph
                );

                if (sensorExistent != null)
                    savedAlert.id = sensorExistent.id;

                sensorDataRepository.save(savedAlert);
                kafkaProducerService.sendMessageOrder(alert);

                System.out.println("Alerta processado: \n)" + alert);
            }
        } catch (Exception e){
            System.out.println("Erro ao salvar/atualizar alerta para a localidade: " + state +"\n"+ e);
        }

        return savedAlert;
    }

    protected List<AlertDTO> verifyIfTemperatureIsOverLimit(String state, Integer days)
    {
        List<AlertDTO> alertList = new ArrayList<AlertDTO>();
        WeatherReturnDTO response = weatherApiExternalService.findForecastByLocation(state, days);

        for (ForecastDayDTO forecast : response.forecast.forecastday){
            DayDTO dayData = forecast.day;

            if(dayData != null){
                CurrentDTO current = response.current;
                AlertDTO alertDTO = new AlertDTO(response.location.name, current.temp_c, current.humidity, current.wind_mph, current.precip_mm, forecast);

                if((dayData.maxtempC != null && current.temp_c > dayData.maxtempC) || (dayData.mintempC != null && current.temp_c < dayData.mintempC))
                    alertDTO.alertType = AlertTypeEnum.TEMPERATURE;

                if(dayData.maxwindMph != null && current.wind_mph > dayData.maxwindMph)
                    alertDTO.alertType = AlertTypeEnum.WIND;

                if(dayData.avghumidity != null && current.humidity > dayData.avghumidity)
                    alertDTO.alertType = AlertTypeEnum.HUMIDITY;

                if(dayData.totalprecipMm != null && current.precip_mm > dayData.totalprecipMm)
                    alertDTO.alertType = AlertTypeEnum.HUMIDITY;

                alertList.add(alertDTO);
            }
        }
        
        return alertList;
    }

    public List<SensorAlert> findAll(){
        return sensorDataRepository.findAll();
    }

    public SensorAlert findByState(String state){
        String normalizedState = state.toLowerCase().trim();
        return sensorDataRepository.findFirstByNormalizedState(normalizedState);
    }
}
