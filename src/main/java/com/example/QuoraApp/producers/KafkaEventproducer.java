package com.example.QuoraApp.producers;

import com.example.QuoraApp.Config.KafkaConfig;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.QuoraApp.events.ViewCountEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaEventproducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishViewCountEvents(ViewCountEvent viewCountEvent) {
        kafkaTemplate.send(KafkaConfig.TOPIC_NAME, viewCountEvent.getTargetId(), viewCountEvent)
                .whenComplete((result, error) -> {
                    if (error != null) {
                        System.out.println("Error publishing view count event:" + error.getMessage());
                    } else {
                        System.out.println(
                                "View count event published successfully:" + result.getRecordMetadata().toString());
                    }
                });
    }

}
