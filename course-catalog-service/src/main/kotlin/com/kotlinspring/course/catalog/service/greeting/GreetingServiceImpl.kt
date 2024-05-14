package com.kotlinspring.course.catalog.service.greeting

import com.kotlinspring.course.catalog.repository.CourseRepository
import com.kotlinspring.course.catalog.service.config.GreetingProperties
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GreetingServiceImp(
    val greetingProperties: GreetingProperties,
    val repository: CourseRepository,
    ) : GreetingService {

    override fun retrieveGreeting(name: String) = "Hello $name, ${greetingProperties.message}"

    override fun list(courseId: UUID) = repository.findAllByCourseId(courseId)

    override fun listAll() = repository.findAllBy()
}
