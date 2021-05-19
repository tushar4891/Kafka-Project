package com.course.kafka.kafkaorder.Command.Action;

import com.course.kafka.kafkaorder.Broker.Message.PromotionMessage;
import com.course.kafka.kafkaorder.Broker.Producer.PromotionProducer;
import com.course.kafka.kafkaorder.Request.PromotionRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PromotionAction {

    @Autowired
    private PromotionProducer producer;


    public void publishToKafka(PromotionRequest request) {

        var message = new PromotionMessage(request.getPromotionCode());

        producer.publish(message);
    }
    
}
