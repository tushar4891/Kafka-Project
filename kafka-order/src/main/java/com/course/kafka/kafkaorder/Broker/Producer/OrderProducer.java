package com.course.kafka.kafkaorder.Broker.Producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.course.kafka.kafkaorder.Broker.Message.OrderMessage;

@Service
public class OrderProducer {
    
    private static final Logger log = LoggerFactory.getLogger(OrderProducer.class);

    @Autowired
    private KafkaTemplate<String, OrderMessage> kafkaTemplate;

    public void publish(OrderMessage message)
    {
        
        kafkaTemplate.send("t.commodity.order", message.getOrderNumber(), message)
                .addCallback(new ListenableFutureCallback<SendResult<String, OrderMessage>>()
                {

                    @Override
                    public void onSuccess(SendResult<String, OrderMessage> result) {
                        log.info("Order {}, item {} Published successfully ", 
                                    message.getOrderNumber(), message.getItemName());          
                    }

                    @Override
                    public void onFailure(Throwable ex) {
                        
                        log.error("Order {} , Item {} failed to publish because {}"
                        , message.getOrderNumber(), message.getItemName(), ex.getMessage());
                    }
                });

        log.info("Just a dummy message for Order {} , Item {} published successfully "
                , message.getOrderNumber(), message.getItemName());
    }
}
