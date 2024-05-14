package com.kotlinspring.course.catalog.service.greeting

import com.kotlinspring.course.catalog.entity.Course
import java.util.UUID

interface GreetingService {
    fun retrieveGreeting(name: String): String

    fun list(courseId: UUID): List<Course>

    fun listAll(): List<Course>
}
