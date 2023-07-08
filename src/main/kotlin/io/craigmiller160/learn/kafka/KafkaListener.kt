package io.craigmiller160.learn.kafka

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaListener {
  @KafkaListener(id = "helloTopicGroup", topics = [KafkaConfig.HELLO_TOPIC])
  fun listenToHelloTopic(message: Message) {
    kafkaMessageHandler("MainListener")(message)
  }
}
