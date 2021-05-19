package com.course.kafka.kafkastream.broker.stream.promotion;

import com.course.kafka.kafkastream.broker.message.PromotionMessage;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;

@Configuration
public class promotionUppercaseSpringJsonStream {
    
    @Bean
    public KStream<String, PromotionMessage> kStreamPromotionUppercase(StreamsBuilder builder)
    {
        var stringSerde = Serdes.String();
        var jsonSerde = new JsonSerde<>(PromotionMessage.class);

        // the string which is in the topic t.commodity.promotion, build/consumed with 
        // serdes and jsonSerde as key value pair.
        //here it gets converted into Java object of PromotionMessage
        KStream<String, PromotionMessage> sourceStream = builder.stream("t.commodity.promotion", 
                                Consumed.with(stringSerde, jsonSerde));
        
        // every stream of uppercaseStream is passed to uppercasePromotionCode()
        // to make it uppercode 
        KStream<String, PromotionMessage> uppercaseStream = sourceStream.mapValues(this::uppercasePromotionCode);

        //uppercaseStream is given to/ produced to t.commodity.promotion-uppercase topics 
        //in json format with the help of stringSerde and jsonSerde.
        uppercaseStream.to("t.commodity.promotion-uppercase", Produced.with(stringSerde, jsonSerde));

        sourceStream.print(Printed.<String,PromotionMessage>toSysOut().withLabel("JSON serde original stream"));
        uppercaseStream.print(Printed.<String,PromotionMessage>toSysOut().withLabel("JSON serde uppercase stream"));

        return sourceStream;

    }

    private PromotionMessage uppercasePromotionCode(PromotionMessage message)
    {
        return new PromotionMessage(message.getPromotionCode().toUpperCase());
    }
}
