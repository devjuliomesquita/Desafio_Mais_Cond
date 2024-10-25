package com.juliomesquita.delivery.infrastructure.broker.kafka.consumer;

import com.juliomesquita.delivery.infrastructure.broker.kafka.producer.CloseOrderMessage;
import com.juliomesquita.delivery.infrastructure.broker.kafka.producer.OrderStatus;
import com.juliomesquita.delivery.infrastructure.broker.kafka.services.SentEventServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DeliveryConsumer {
    private static final Logger log = LoggerFactory.getLogger(DeliveryConsumer.class);
    private final SentEventServiceImpl sentEventService;

    public DeliveryConsumer(final SentEventServiceImpl sentEventService) {
        this.sentEventService = Objects.requireNonNull(sentEventService);
    }

    @KafkaListener(topics = "preparation-topic")
    public void execute(final CreateDeliveryMessage anEvent) {
        log.info("Mensagem recebida :: {}, garçom chamado", anEvent);
        this.sleep();
        this.sentEventService.sentEvent(new CloseOrderMessage(anEvent.orderId(), OrderStatus.COMPLETED));
    }

    private void sleep(){
        log.info("Esperando garçom chegar");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Erro ao aguardar: " + e.getMessage());
        }
    }
}
