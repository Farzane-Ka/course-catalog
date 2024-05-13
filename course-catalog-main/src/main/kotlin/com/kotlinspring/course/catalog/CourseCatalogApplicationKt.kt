package com.kotlinspring.course.catalog

import com.kotlinspring.course.catalog.service.config.GreetingProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(GreetingProperties::class)
class CourseCatalogApplicationKt

fun main(args: Array<String>) {
	runApplication<CourseCatalogApplicationKt>(*args)
}
