package com.kotlinspring.course.catalog.controller.util

import com.kotlinspring.course.catalog.model.course.CourseDTO
import com.kotlinspring.course.catalog.model.entity.Course
import com.kotlinspring.course.catalog.model.entity.Instructor
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

fun courseDTOList() = listOf(
    CourseDTO(null, "Math course1", "Mathematics"),
    CourseDTO(null, "Physics course1", "Physics"),
)

fun courseEntityList(instructor: Instructor? = null) = listOf(
    Course(null,
        "Build RestFul APis using SpringBoot and Kotlin", "Development",
        instructor),
    Course(null,
        "Build Reactive Microservices using Spring WebFlux/SpringBoot", "Development"
        ,instructor
    ),
    Course(null,
        "Java Developers", "Development" ,
        instructor)
)

fun instructorEntity(name : String = "Farzane Karami")
    = Instructor(null, name)
