package io.craigmiller160.learn.kafka

import org.springframework.kafka.listener.MessageListener

fun kafkaMessageHandler(name: String): MessageListener<Message, Unit> = MessageListener { message ->
  println("[$name Handler]: $message")
}
