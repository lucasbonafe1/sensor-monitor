package com.example.sensor_monitor.services;

import com.example.sensor_monitor.dtos.SensorDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    @Autowired
    private KafkaTemplate<String, SensorDataDTO> kafkaTemplateOrder;

    @SuppressWarnings("null")
    public void sendMessageOrder(SensorDataDTO order) {
        int partition = 1;
        System.out.println("Sent message to partition: " + partition);
        System.out.println("Sending Order: " + order.state);

        kafkaTemplateOrder.send("sensor-order-processed",partition, null, order);
    }
}
