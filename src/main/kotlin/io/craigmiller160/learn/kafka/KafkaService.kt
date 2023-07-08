package io.craigmiller160.learn.kafka

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.KafkaOperations
import org.springframework.kafka.listener.ContainerProperties
import org.springframework.kafka.listener.KafkaMessageListenerContainer
import org.springframework.kafka.support.TopicPartitionOffset
import org.springframework.stereotype.Service

@Service
class KafkaService(
    private val consumerFactory: ConsumerFactory<Any, Any>,
    private val template: KafkaOperations<Any, Any>
) {
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
            .also { props -> props.setGroupId("helloTopicGroup2") }
            .let { KafkaMessageListenerContainer(consumerFactory, it) }
            .also { listener -> listener.setupMessageListener(kafkaMessageHandler("name")) }
    synchronized(listenersLock) { listeners += listener }
  }

  fun stopListeners() {
    val localListeners =
        synchronized(listenersLock) {
          val listenersCopy = listeners.map { it }
          listeners.clear()
          listenersCopy
        }
    localListeners.forEach { it.stop() }
  }

  fun publishToHelloTopic(message: Message) {
    println("Publishing to HelloTopic Message: $message")
    template.send(KafkaConfig.HELLO_TOPIC, message)
  }
}
