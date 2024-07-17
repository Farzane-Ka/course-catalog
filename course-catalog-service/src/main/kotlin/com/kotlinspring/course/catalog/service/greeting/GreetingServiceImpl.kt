package com.kotlinspring.course.catalog.service.greeting

import com.kotlinspring.course.catalog.service.config.GreetingProperties
import org.springframework.stereotype.Service

@Service
class GreetingServiceImp(
    val greetingProperties: GreetingProperties,
    ) : GreetingService {

    override fun retrieveGreeting(name: String) = "Hello $name, ${greetingProperties.message}"
}
