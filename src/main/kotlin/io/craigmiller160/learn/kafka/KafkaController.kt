package io.craigmiller160.learn.kafka

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/kafka")
class KafkaController(private val kafkaService: KafkaService) {
  @PostMapping("/hello")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun publishToHelloTopic(@RequestBody message: Message) {
    kafkaService.publishToHelloTopic(message)
  }

  @PostMapping("/listeners/{name}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun addListener(@PathVariable name: String) {
    kafkaService.addListener(name)
  }

  @DeleteMapping("/listeners")
  fun stopListeners() {
    kafkaService.stopListeners()
  }
}
