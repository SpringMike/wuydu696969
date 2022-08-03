package com.example.ex_08.producer.rabbitMQ;

import com.example.ex_08.dto.StatisticalDTO;
import com.springrabbitmq.config.MQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StatisticalRabbitMQProducer {
    @PersistenceContext
    private final EntityManager entityManager;

    private final RabbitTemplate rabbitTemplate;

    public List<StatisticalDTO> produce() {
        List<StatisticalDTO> list = this.entityManager.createNamedQuery("getFeaturedStatistical").getResultList();
        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE,MQConfig.ROUTING_KEY,list);
        return list;
    }
}
