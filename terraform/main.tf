terraform {
  required_providers {
    kafka = {
      source = "Mongey/kafka"
      version = "0.5.3"
    }
  }
}

provider "kafka" {
  bootstrap_servers = ["localhost:9092"]
}

resource "kafka_topic" "tf_topic" {
  name               = "tf-topic"
  partitions         = 1
  replication_factor = 1
}