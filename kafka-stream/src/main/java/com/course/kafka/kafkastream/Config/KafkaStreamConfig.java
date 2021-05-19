package com.course.kafka.kafkastream.Config;

import java.util.HashMap;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

@Configuration
@EnableKafkaStreams
public class KafkaStreamConfig {
    
    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kafkaStreamConfig()
    {
        var props = new HashMap<String, Object>();

        //Every kafka stream application must be identified by unique application ID
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-stream");

        //It is bootstrap server configuration
        //A bootstrap server is a commoa seperated list of host - port pair 
        //that stream application would use to establish the initial connection
        //with the kafka clusters
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");

        //serdes is a factory class that combines serializer and deserializer
        //here we are setting Integer serde for key and String serde for value
        // Kafka stream application would read a data and write it as well
        //hence they internally create a combination of conusmer & producer
        //so they need serializer as well as deserializer. So stream takes the serialize
        // deserialize approach for key and value
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,Serdes.String().getClass().getName());

        return new KafkaStreamsConfiguration(props);
    }
}
