package com.course.kafka.kafkaorder.Config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    
    @Bean
	public NewTopic topicOrder() {
		return TopicBuilder.name("t.commodity.order").partitions(2).replicas(1).build();
	}

	@Bean
	public NewTopic newTopicOrder() {
		return TopicBuilder.name("t.commodity.promotion").partitions(1).replicas(1).build();
	}

	// @Bean
	// public NewTopic newTopicOrder1() {
	// 	return TopicBuilder.name("t.commodity.promotion-uppercase").partitions(2).replicas(1).build();
	// }
}
