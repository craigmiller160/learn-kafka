package io.craigmiller160.learn.kafka

import org.springframework.kafka.listener.GenericMessageListener

fun kafkaMessageHandler(name: String): GenericMessageListener<Message> =
    GenericMessageListener { message ->
      println("[$name Handler]: $message")
    }
