package com.kotlinspring.course.catalog.persistence.repository

import com.kotlinspring.course.catalog.model.entity.Course

interface CustomCourseRepository {

    fun persistAll(courses: List<Course>): List<Course>

    fun listAll(): List<Course>
}
