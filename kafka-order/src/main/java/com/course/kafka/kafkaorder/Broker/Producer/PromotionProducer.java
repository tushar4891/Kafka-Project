package com.course.kafka.kafkaorder.Broker.Producer;


import java.util.concurrent.ExecutionException;

import com.course.kafka.kafkaorder.Broker.Message.PromotionMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PromotionProducer {
    
    private static final Logger log = LoggerFactory.getLogger(PromotionProducer.class);

    @Autowired
    private KafkaTemplate<String,PromotionMessage> kafkaTemplate;

    public void publish(PromotionMessage message)
    {
        // this is the synchronous way to send message on kafka which
        // is not recommended 
        try 
        {
            var sendResult = kafkaTemplate.send("t.commodity.promotion",message).get();

            log.info("Send result sucess for message {}", sendResult.getProducerRecord().value());
        } catch (InterruptedException | ExecutionException e) 
        {                
            log.error("Error publishing {}, cause {}", message, e.getMessage());
        }
    }
}
