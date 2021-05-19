package com.course.kafka.kafkapattern.Broker.consumer;

import com.course.kafka.kafkapattern.Broker.Message.OrderMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderListener {
    
    private static final Logger log = LoggerFactory.getLogger(OrderListener.class);

    @KafkaListener(topics = "t.commodity.oroder")
    public void listen(OrderMessage message)
    {
        var totalItemAmount = message.getPrice() * message.getQuantity();

        log.info("Processing order : {}, Item : {}, credit card number {}. Total amount for this number is {}",
                    message.getOrderNumber(), message.getItemName(), message.getCreditCardNumber(),
                    totalItemAmount);
            
        
    }
}
