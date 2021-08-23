package com.epam.digital.data.platform.reportexporter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

@Configuration
public class KafkaConfig {

  @Bean
  public <O>
  ConcurrentKafkaListenerContainerFactory<String, O> concurrentKafkaListenerContainerFactory(
      ConsumerFactory<String, O> cf) {
    ConcurrentKafkaListenerContainerFactory<String, O> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(cf);
    return factory;
  }

}
