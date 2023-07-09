package io.craigmiller160.learn.kafka

import org.springframework.kafka.listener.MessageListener

fun kafkaMessageHandler(name: String): (Message) -> Unit = { message ->
  println("[$name Handler]: $message")
}

fun kafkaMessageListener(name: String): MessageListener<Message, Unit> = kafkaMessageListener(name)
