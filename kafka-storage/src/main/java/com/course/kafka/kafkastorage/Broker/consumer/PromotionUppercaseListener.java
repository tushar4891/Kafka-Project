package com.course.kafka.kafkastorage.Broker.consumer;

import com.course.kafka.kafkastorage.Broker.Message.PromotionMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PromotionUppercaseListener {
    
    private static final Logger log = LoggerFactory.getLogger(PromotionUppercaseListener.class);

    @KafkaListener(topics = "t.commodity.promotion-uppercase")
    public void listenPromotion(PromotionMessage message)
    {
        log.info("Processing uppercase promotion {}", message);
    }

}
