package com.course.kafka.kafkaorder.Command.Service;

import com.course.kafka.kafkaorder.Command.Action.OrderAction;
import com.course.kafka.kafkaorder.Entity.Order;
import com.course.kafka.kafkaorder.Request.OrderRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderAction action;
    
    public String saveOrder(OrderRequest request) {
        
        Order order = action.convertToOrder(request);

        action.saveToDatabase(order);

        order.getItems().forEach(action::publishToKafka);

        return order.getOrderNumber();
    }
    
}
