package com.course.kafka.kafkaorder.Repository;

import com.course.kafka.kafkaorder.Entity.OrderItem;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Integer> {
    
}
