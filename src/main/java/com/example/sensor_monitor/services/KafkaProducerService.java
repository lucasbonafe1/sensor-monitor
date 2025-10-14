package com.example.sensor_monitor.services;

import com.example.sensor_monitor.dtos.AlertDTO;
import com.example.sensor_monitor.entities.SensorAlert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    @Autowired
    private KafkaTemplate<String, AlertDTO> kafkaTemplateOrder;

    @SuppressWarnings("null")
    public void sendMessageOrder(AlertDTO order) {
        int partition = 1;
        System.out.println("Sent message to partition: " + partition);
        System.out.println("Tipo do alerta: " + order.alertType.name());

        kafkaTemplateOrder.send("sensor-order-processed",partition, null, order);
    }
}
