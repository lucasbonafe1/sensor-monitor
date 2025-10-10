package com.example.sensor_monitor.repositories;
import com.example.sensor_monitor.dtos.SensorDataDTO;
import com.example.sensor_monitor.entities.SensorAlert;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface SensorDataRepository extends MongoRepository<SensorAlert, String> {
    public SensorAlert findFirstByNormalizedState(String normalizedState);
}
