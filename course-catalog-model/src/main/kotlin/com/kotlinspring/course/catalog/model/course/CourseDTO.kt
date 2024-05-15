package com.kotlinspring.course.catalog.model.course

import java.util.*

data class CourseDTO(
    val courseId: UUID?,
    var name: String,
    var category: String,
)