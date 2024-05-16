package com.kotlinspring.course.catalog.model.course

import jakarta.validation.constraints.NotBlank
import java.util.*

data class CourseDTO(
    val courseId: UUID?,
    @get:NotBlank(message = "Course name cannot be blank")
    var name: String,
    @get:NotBlank(message = "Course category cannot be blank")
    var category: String,
)