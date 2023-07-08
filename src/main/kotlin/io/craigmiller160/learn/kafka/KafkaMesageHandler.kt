package io.craigmiller160.learn.kafka

fun kafkaMessageHandler(name: String): (Message) -> Unit = { message ->
  println("[$name Handler]: $message")
}
