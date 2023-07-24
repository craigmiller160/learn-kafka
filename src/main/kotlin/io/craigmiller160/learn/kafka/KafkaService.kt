package io.craigmiller160.learn.kafka

import java.lang.RuntimeException
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaService {
  private val messages = mutableListOf<Message>()

  @KafkaListener(id = "helloTopicGroup", topics = [KafkaConfig.HELLO_TOPIC])
  fun listenToHelloTopic(message: Message) {
    println("RECEIVED MESSAGE: $message")
    if ("Fail" == message.body && messages.isEmpty()) {
      throw RuntimeException("Dying")
    }
    messages += message
    println("ALL MESSAGES")
    println(messages)
  }
}
