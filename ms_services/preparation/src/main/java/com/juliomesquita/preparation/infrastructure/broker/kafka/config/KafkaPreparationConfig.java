package com.juliomesquita.preparation.infrastructure.broker.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaPreparationConfig {
    @Bean
    public NewTopic preparationTopic(){
        return TopicBuilder
                .name("preparation-topic")
                .build();
    }

    @Bean
    public NewTopic preparationOrderTopic(){
        return TopicBuilder
                .name("preparation-order-topic")
                .build();
    }
}
