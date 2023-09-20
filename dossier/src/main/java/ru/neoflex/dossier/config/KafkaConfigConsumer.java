package ru.neoflex.dossier.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import ru.neoflex.dossier.enums.Theme;
import ru.neoflex.dossier.serializer.ThemeDeserializer;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class KafkaConfigConsumer {
    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addDeserializer(Theme.class, new ThemeDeserializer());
        objectMapper.registerModule(module);

        return objectMapper;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory(KafkaProperties kafkaProperties, ObjectMapper objectMapper){
        log.trace("KafkaConfigProducer.producerFactory - KafkaProperties{}, ObjectMapper {}", kafkaProperties, objectMapper);

        Map<String, Object> props = kafkaProperties.buildConsumerProperties();

        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        DefaultKafkaConsumerFactory<String,String> kafkaConsumerFactory = new DefaultKafkaConsumerFactory<>(props);
        log.trace("KafkaConfig.producerFactory - DefaultKafkaProducerFactory{}",kafkaConsumerFactory);
        return kafkaConsumerFactory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(KafkaProperties kafkaProperties, ObjectMapper objectMapper) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(kafkaProperties,objectMapper));
        return factory;
    }
}