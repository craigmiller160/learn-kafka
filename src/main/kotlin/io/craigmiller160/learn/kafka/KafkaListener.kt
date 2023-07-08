package io.craigmiller160.learn.kafka

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.listener.ContainerProperties
import org.springframework.kafka.listener.KafkaMessageListenerContainer
import org.springframework.kafka.support.TopicPartitionOffset
import org.springframework.stereotype.Component

@Component
class KafkaListener(private val consumerFactory: ConsumerFactory<Any, Any>) {
  private val listenersLock = Any()
  private val listeners = mutableListOf<KafkaMessageListenerContainer<Any, Any>>()

  @KafkaListener(id = "helloTopicGroup", topics = [KafkaConfig.HELLO_TOPIC])
  fun listenToHelloTopic(message: Message) {
    kafkaMessageHandler("MainListener")(message)
  }

  fun addListener(name: String) {
    val listener =
        TopicPartitionOffset(KafkaConfig.HELLO_TOPIC, 0)
            .let { ContainerProperties(it) }
            .let { KafkaMessageListenerContainer(consumerFactory, it) }
            .also { listener -> listener.setupMessageListener(kafkaMessageHandler("name")) }
    synchronized(listenersLock) { listeners += listener }
  }

  fun stopListeners() {
    // TODO need local copy and synchronized
    listeners.forEach { listener -> listener.stop() }
    listeners.clear()
  }
}
