package io.craigmiller160.learn.kafka

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaOperations
import org.springframework.kafka.listener.CommonErrorHandler
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer
import org.springframework.kafka.listener.DefaultErrorHandler
import org.springframework.kafka.support.converter.JsonMessageConverter
import org.springframework.kafka.support.converter.RecordMessageConverter
import org.springframework.util.backoff.FixedBackOff

@Configuration
class KafkaConfig {
  companion object {
    const val HELLO_TOPIC = "hello-topic"
  }
  @Bean
  fun errorHandler(template: KafkaOperations<Any, Any>): CommonErrorHandler =
      DefaultErrorHandler(DeadLetterPublishingRecoverer(template), FixedBackOff(10_000L, 5))

  @Bean fun converter(): RecordMessageConverter = JsonMessageConverter()
}
