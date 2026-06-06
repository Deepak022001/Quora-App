package com.example.QuoraApp.Config;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;

@Configuration
@EnableKafka
public class KafkaConfig {
    @Value("${spring.kafka.bootstrap-servers:localhost:9892}")
    private String bootstrapserver;

    @Value("${spring.kafka.consumer.group-id:view-count-consumer}")
    private String groupId;

    public static final String TOPIC_NAME="view-count";

    // QuoraApp — wants to send any object (ViewEvent, AnswerEvent, QuestionEvent)
    // ProducerFactory<String, Object>
    @Bean
    public ProducerFactory<String,Object>producerFactory(){
        Map<String,Object>configProps=new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapserver);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return  new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public ConsumerFactory<String,Object>consumerFactory(){
        Map<String,Object>configProps=new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapserver);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,JsonDeserialize.class);
        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String,Object>kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,Object>kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,Object>factory=new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3);
        return factory;
    }
}
