package com.kotlinspring.course.catalog.service.greeting

import com.kotlinspring.course.catalog.persistence.repository.CourseRepository
import com.kotlinspring.course.catalog.service.config.GreetingProperties
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GreetingServiceImp(
    val greetingProperties: GreetingProperties,
    ) : GreetingService {

    override fun retrieveGreeting(name: String) = "Hello $name, ${greetingProperties.message}"
}
