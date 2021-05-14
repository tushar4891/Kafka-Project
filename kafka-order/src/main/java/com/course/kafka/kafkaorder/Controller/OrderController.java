package com.course.kafka.kafkaorder.Controller;

import com.course.kafka.kafkaorder.Command.Service.OrderService;
import com.course.kafka.kafkaorder.Request.OrderRequest;
import com.course.kafka.kafkaorder.Response.OrderResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/order")
public class OrderController 
{
    @Autowired
    private OrderService service;

    @PostMapping(value="", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   //@PostMapping
    public ResponseEntity<OrderResponse>createOrder(@RequestBody OrderRequest request)
    {
      
        String orderNumber = service.saveOrder(request);

        OrderResponse  orderResponse = new OrderResponse(orderNumber);

        return ResponseEntity.ok().body(orderResponse);
    }

}
