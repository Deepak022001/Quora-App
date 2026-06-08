package com.example.QuoraApp.consumers;

import com.example.QuoraApp.Config.KafkaConfig;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.example.QuoraApp.events.ViewCountEvent;
import com.example.QuoraApp.repositories.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaEventConsumers {
    private final QuestionRepository questionRepository;

    @KafkaListener(topics = KafkaConfig.TOPIC_NAME, groupId = "view-count-consumer", containerFactory = "kafkaListenerContainerFactory")
    public void handleViewCountEvent(ViewCountEvent viewCountEvent) {
        questionRepository
                .findById(viewCountEvent.getTargetId())
                .flatMap(question -> {
                    Integer currentViews = question.getViews() != null ? question.getViews() : 0;
                    question.setViews(currentViews + 1);
                    question.setViewCount(currentViews + 1);
                    return questionRepository.save(question);
                })
                .subscribe(updateQuestions -> {
                    System.out.println("Question updated successfully:" + updateQuestions);
                }, error -> {
                    System.out.println("Error updating question:" + error.getMessage());
                });
    }
}
