package com.juliomesquita.menu.infrastruture.broker.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaMenuConfig {
    @Bean
    public NewTopic menuTopic() {
        return TopicBuilder
                .name("menu-topic")
                .build();
    }
}
