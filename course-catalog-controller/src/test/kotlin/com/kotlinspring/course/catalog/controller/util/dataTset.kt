package com.kotlinspring.course.catalog.controller.util

import com.kotlinspring.course.catalog.model.course.CourseDTO
import com.kotlinspring.course.catalog.model.entity.Course
import java.util.UUID

fun courseDTO(
    courseId: UUID? = null,
    name: String = "math",
    category: String = "Mathematics",
) = CourseDTO(
    courseId = courseId,
    name = name,
    category = category,
)

fun courseList() = listOf(
    Course(null, "Math course1", "Mathematics"),
    Course(null, "Physics course1", "Physics"),
)

fun courseDTOList() = listOf(
    CourseDTO(null, "Math course1", "Mathematics"),
    CourseDTO(null, "Physics course1", "Physics"),
)
