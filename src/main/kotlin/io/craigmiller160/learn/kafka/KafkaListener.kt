package io.craigmiller160.learn.kafka

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaListener {
  @KafkaListener(id = "helloTopicGroup", topics = ["HelloTopic"])
  fun listenToHelloTopic(message: Message) {
    println("[1] KAFKA MESSAGE RECEIVED HelloTopic Message: $message")
  }

  @KafkaListener(id = "helloTopicGroup2", topics = ["HelloTopic"])
  fun listenToHelloTopic2(message: Message) {
    println("[2] KAFKA MESSAGE RECEIVED HelloTopic Message: $message")
  }

  @KafkaListener(id = "helloTopicGroup3", topics = ["HelloTopic"])
  fun listenToHelloTopic3(message: Message) {
    println("[3] KAFKA MESSAGE RECEIVED HelloTopic Message: $message")
  }
}
