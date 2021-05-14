package com.course.kafka.kafkaorder.Command.Action;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.course.kafka.kafkaorder.Broker.Message.OrderMessage;
import com.course.kafka.kafkaorder.Broker.Producer.OrderProducer;
import com.course.kafka.kafkaorder.Entity.Order;
import com.course.kafka.kafkaorder.Entity.OrderItem;
import com.course.kafka.kafkaorder.Repository.OrderItemRepository;
import com.course.kafka.kafkaorder.Repository.OrderRepository;
import com.course.kafka.kafkaorder.Request.OrderItemRequest;
import com.course.kafka.kafkaorder.Request.OrderRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderAction {

    @Autowired
    private OrderProducer producer;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public Order convertToOrder(OrderRequest request) 
    {    
        var result = new Order();

        result.setCreditCardNumber(request.getCreditCardNumber());
        result.setOrderLocation(request.getOrderLocation());
        result.setOrderDateTime(LocalDateTime.now());
        result.setOrderNumber(RandomStringUtils.randomAlphanumeric(8).toUpperCase());

        List<OrderItem> items = request.getItems().stream().map(this::convertToOrderItem).collect(Collectors.toList());
        items.forEach(item->item.setOrder(result));

        result.setItems(items);

        return result;
    }

    private OrderItem convertToOrderItem(OrderItemRequest itemRequest)
    {
        var result = new OrderItem();

        result.setItemName(itemRequest.getItemName());
        result.setPrice(itemRequest.getPrice());
        result.setQuantity(itemRequest.getQuantity());

        return result;
    }

    public void saveToDatabase(Order order) {

        orderRepository.save(order);
        order.getItems().forEach(orderItemRepository::save);
    }
    
    public void publishToKafka(OrderItem item)
    {
        var orderMessage = new OrderMessage();

		orderMessage.setItemName(item.getItemName());
		orderMessage.setPrice(item.getPrice());
		orderMessage.setQuantity(item.getQuantity());

		orderMessage.setOrderDateTime(item.getOrder().getOrderDateTime());
		orderMessage.setOrderLocation(item.getOrder().getOrderLocation());
		orderMessage.setOrderNumber(item.getOrder().getOrderNumber());
		orderMessage.setCreditCardNumber(item.getOrder().getCreditCardNumber());

		producer.publish(orderMessage);
    }
}
