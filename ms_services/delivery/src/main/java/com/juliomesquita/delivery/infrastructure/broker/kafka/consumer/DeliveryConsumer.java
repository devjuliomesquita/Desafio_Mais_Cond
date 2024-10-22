package com.juliomesquita.delivery.infrastructure.broker.kafka.consumer;

import com.juliomesquita.delivery.infrastructure.broker.kafka.producer.CloseOrderMessage;
import com.juliomesquita.delivery.infrastructure.broker.kafka.producer.OrderStatus;
import com.juliomesquita.delivery.infrastructure.broker.kafka.services.SentEventServiceImpl;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DeliveryConsumer {
    private final SentEventServiceImpl sentEventService;

    public DeliveryConsumer(final SentEventServiceImpl sentEventService) {
        this.sentEventService = Objects.requireNonNull(sentEventService);
    }

    @KafkaListener(topics = "preparation-topic")
    public void execute(final CreateDeliveryMessage anEvent) {
        System.out.println("chegou no kafka listener :: %s".formatted(anEvent));
        this.sleep();
        this.sentEventService.sentEvent(new CloseOrderMessage(anEvent.orderId(), OrderStatus.COMPLETED));
    }

    private void sleep(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Erro ao aguardar: " + e.getMessage());
        }
    }
}
