import com.diffplug.gradle.spotless.SpotlessExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val projectGroup: String by project
val projectVersion: String by project

plugins {
    id("org.springframework.boot") version "3.0.4"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm")
    kotlin("plugin.spring")
    id("io.craigmiller160.gradle.defaults") version "1.2.2"
    id("com.diffplug.spotless") version "6.17.0"
}

group = projectGroup
version = projectVersion
java.sourceCompatibility = JavaVersion.VERSION_19

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict", "-Xcontext-receivers")
        jvmTarget = "19"
    }
}

configure<SpotlessExtension> {
    kotlin {
        ktfmt()
    }
}