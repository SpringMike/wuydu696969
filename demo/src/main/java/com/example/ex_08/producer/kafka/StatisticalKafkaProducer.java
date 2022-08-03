package com.example.ex_08.producer.kafka;

import com.example.ex_08.dto.StatisticalDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticalKafkaProducer  {

    @PersistenceContext
    private final EntityManager entityManager;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${order.topic.name}")
    private String topicName;


    public List<StatisticalDTO> getStatistical() {
        ObjectMapper om=new ObjectMapper();
        List<StatisticalDTO> list = this.entityManager.createNamedQuery("getFeaturedStatistical").getResultList();
        String mess = null;
        try {
            mess = om.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        kafkaTemplate.send(topicName,mess);
        return list;
    }
}
