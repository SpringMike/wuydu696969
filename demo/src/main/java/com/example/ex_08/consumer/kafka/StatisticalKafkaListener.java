package com.example.ex_08.consumer.kafka;


import com.example.ex_08.dto.StatisticalDTO;
import com.example.ex_08.model.entity.Statistical;
import com.example.ex_08.repo.IStatisticalRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StatisticalKafkaListener {
    private final IStatisticalRepo statisticalRepo;

    ObjectMapper object = new ObjectMapper();

    @Value("${order.topic.name}")
    private String topicName;

    @KafkaListener(topics = "statistical-topic", groupId = "foo")
    public void saveStatistical(String mess) {
        List<StatisticalDTO> list = null;
        ModelMapper modelMapper = new ModelMapper();

        System.out.println(mess);
        try {
            TypeReference<List<StatisticalDTO>> typeReference = new TypeReference<>() {};
            list =  object.readValue(mess,typeReference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        for(StatisticalDTO statisticalDTO: list){
            Statistical statistical = modelMapper.map(statisticalDTO, Statistical.class);
            statisticalRepo.save(statistical);
        }
    }
}
