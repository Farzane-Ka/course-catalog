package com.kotlinspring.course.catalog.service.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Profile("dev")
@Configuration
class GreetingPropertiesTest {

    private val greetingMessage = System.getProperty("greeting.message")!!

    @Bean
    fun greetingProperties() = GreetingProperties(message = greetingMessage)
}
