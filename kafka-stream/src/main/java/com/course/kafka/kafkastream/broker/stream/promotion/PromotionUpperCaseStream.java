package com.course.kafka.kafkastream.broker.stream.promotion;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//@Configuration
public class PromotionUpperCaseStream {
    
    // It is a computational logic
    @Bean
    public KStream<String,String> kstreamPromotionUppercase(StreamsBuilder builder)
    {
       // Opening a stream to a source topic and returns its object
        KStream<String,String> sourceStream =  builder.stream("t.commodity.promotion", Consumed.with(Serdes.String(),Serdes.String()));

        // process the stream i.e. apply the logic
        KStream<String,String> uppercaseStream = sourceStream.mapValues(s->s.toUpperCase());
        uppercaseStream.to("t.commodity.promotion-uppercase");

        //useful for debugging, but dont do this on production
        sourceStream.print(Printed.<String,String> toSysOut().withLabel("Original Stream"));
        uppercaseStream.print(Printed.<String,String> toSysOut().withLabel("Uppercase Stream"));

        sourceStream.foreach((k,v)->System.out.println("key" + k + " value "+ v));
       
        return sourceStream;
    }

}

