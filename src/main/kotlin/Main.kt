package com.tschumacher.system

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

fun main(args: Array<String>) {
    println("Hello World!")
    runApplication<AnalyticsApplication>(*args)
    runApplication<UrlShorteningApplication>(*args)

}
@EnableJpaRepositories(basePackages = ["com.example.analytics.repository"])
class AnalyticsApplication

//@EnableJpaRepositories(basePackages = ["com.example.event.repository"])
@SpringBootApplication
class UrlShorteningApplication
