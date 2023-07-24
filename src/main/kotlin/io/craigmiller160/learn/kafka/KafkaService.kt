package io.craigmiller160.learn.kafka

import java.lang.RuntimeException
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaService {

  @KafkaListener(id = "helloTopicGroup", topics = [KafkaConfig.HELLO_TOPIC])
  fun listenToHelloTopic(message: Message) {
    println("RECEIVED MESSAGE: $message")
    throw RuntimeException("Dying")
  }
}
