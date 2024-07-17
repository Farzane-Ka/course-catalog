package com.kotlinspring.course.catalog.persistence.dataset

import com.kotlinspring.course.catalog.model.entity.Course

fun courseList() = listOf(
    Course(null, "math1", "mathematics"),
    Course(null, "physics1", "physics"),
    Course(null, "chemist1", "chemistry")
)
