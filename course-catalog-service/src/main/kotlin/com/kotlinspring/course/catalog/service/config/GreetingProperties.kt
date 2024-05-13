package com.kotlinspring.course.catalog.service.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Profile

@Profile("default")
@ConfigurationProperties(prefix = "greeting")
data class GreetingProperties(
    val message: String = ""
)
