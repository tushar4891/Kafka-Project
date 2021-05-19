package com.course.kafka.kafkaorder.Broker.Producer;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.ArrayList;
import java.util.List;

import com.course.kafka.kafkaorder.Broker.Message.OrderMessage;

@Service
public class OrderProducer {
    
    private static final Logger log = LoggerFactory.getLogger(OrderProducer.class);

    @Autowired
    private KafkaTemplate<String, OrderMessage> kafkaTemplate;

    //Creates a record to be sent to a specified topic and partition
    private ProducerRecord<String,OrderMessage> buildProducerRecord(OrderMessage message)
    {
        int surpriceBonus = StringUtils.startsWithIgnoreCase(message.getOrderLocation(),"A") ? 25 : 15;

        List<Header>headers = new ArrayList<>();
        var surpriseBonusHeader = new RecordHeader("surpriseBonusHeader", Integer.toString(surpriceBonus).getBytes());

        headers.add(surpriseBonusHeader);

        return new ProducerRecord<String,OrderMessage>("t.commodity.order", null,message.getOrderNumber(),
            message, headers);
 
    }
    public void publish(OrderMessage message)
    {
        var producedRecord = buildProducerRecord(message);

        // this is the way of sending asynchronous message passing to kafka
        // with call back mechanism. So if due to network delay or consumer is 
        // not available then exception is thrown and after ideally 5 exception
        // message is blocked otherwise message sending will happen continuously.
        
        kafkaTemplate.send(producedRecord)
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
