package com.thechance.jokes

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class JokesApplication

fun main(args: Array<String>) {
	runApplication<JokesApplication>(*args)
}
