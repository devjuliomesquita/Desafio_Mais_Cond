package com.juliomesquita.preparation.infrastructure.configurations.beans.order;

import com.juliomesquita.preparation.infrastructure.services.order.OrderClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderServiceBeansConfig {

    @Bean
    public OrderClient orderClient(){
        return null;
    }
}
