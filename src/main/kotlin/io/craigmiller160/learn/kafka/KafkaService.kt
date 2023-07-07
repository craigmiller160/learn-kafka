package io.craigmiller160.learn.kafka

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaOperations
import org.springframework.stereotype.Service

@Service
class KafkaService(private val template: KafkaOperations<Any, Any>) {
  @KafkaListener(id = "helloTopicGroup", topics = ["HelloTopic"])
  fun listenToHelloTopic(message: Message) {
    println("Received HelloTopic Message: $message")
  }

  fun publishToHelloTopic(message: Message) {
    println("Publishing to HelloTopic Message: $message")
    template.send("HelloTopic", message)
  }
}
