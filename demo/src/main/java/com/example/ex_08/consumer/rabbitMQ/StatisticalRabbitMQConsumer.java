package com.example.ex_08.consumer.rabbitMQ;

import com.example.ex_08.dto.StatisticalDTO;
import com.example.ex_08.model.entity.Statistical;
import com.example.ex_08.repo.IStatisticalRepo;
import com.springrabbitmq.config.MQConfig;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StatisticalRabbitMQConsumer {
    private final RabbitTemplate rabbitTemplate;

    private final IStatisticalRepo statisticalRepo;

    @RabbitListener(queues = MQConfig.QUEUE)
    public void receiveMess(List<StatisticalDTO> list) {
        ModelMapper modelMapper = new ModelMapper();
        if (list != null) {
            for (StatisticalDTO statisticalDTO : list){
                Statistical statistical = modelMapper.map(statisticalDTO,Statistical.class);
                statisticalRepo.save(statistical);
            }
        }
    }
}
