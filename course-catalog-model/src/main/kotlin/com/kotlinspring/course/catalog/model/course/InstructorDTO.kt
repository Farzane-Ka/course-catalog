package com.kotlinspring.course.catalog.model.course

import jakarta.validation.constraints.NotBlank
import java.util.*

data class InstructorDTO(
    val instructorId: UUID?,
    @get:NotBlank(message = "Instructor name cannot be blank")
    var name: String,
)
