package com.course.kafka.kafkaorder.Command.Service;

import com.course.kafka.kafkaorder.Command.Action.PromotionAction;
import com.course.kafka.kafkaorder.Request.PromotionRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionService {
    
    @Autowired
    private PromotionAction action;

    public void createPromotion(PromotionRequest request)
    {
        action.publishToKafka(request);
        
    }
}
