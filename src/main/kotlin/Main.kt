package com.tschumacher.system

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

fun main(args: Array<String>) {
    println("Hello World!")
    runApplication<DemoApplication>(*args)

}

@SpringBootApplication
class DemoApplication
