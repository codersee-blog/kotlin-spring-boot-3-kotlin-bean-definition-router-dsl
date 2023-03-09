package com.codersee.kotlindsl

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlindslApplication

fun main(args: Array<String>) {
	runApplication<KotlindslApplication>(*args)
}
