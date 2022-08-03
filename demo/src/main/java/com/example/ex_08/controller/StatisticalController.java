package com.example.ex_08.controller;


import com.example.ex_08.consumer.kafka.StatisticalKafkaListener;
import com.example.ex_08.consumer.rabbitMQ.StatisticalRabbitMQConsumer;
import com.example.ex_08.dto.StatisticalDTO;
import com.example.ex_08.producer.kafka.StatisticalKafkaProducer;
import com.example.ex_08.producer.rabbitMQ.StatisticalRabbitMQProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class StatisticalController {

    private final StatisticalRabbitMQConsumer statisticalRabbitMQConsumer;
    private final StatisticalRabbitMQProducer statisticalRabbitMQProducer;

    private final StatisticalKafkaProducer statisticalKafkaProducer;

    private final StatisticalKafkaListener statisticalKafkaListener;

    @GetMapping("/rabbit")
    public ResponseEntity<?> getLogs() {
        List<StatisticalDTO> list = statisticalRabbitMQProducer.produce();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/kafka")
    public ResponseEntity<?> getLogsKafka() {
        List<StatisticalDTO> list = statisticalKafkaProducer.getStatistical();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
