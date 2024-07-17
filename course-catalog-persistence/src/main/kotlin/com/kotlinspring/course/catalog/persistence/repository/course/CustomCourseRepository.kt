package com.kotlinspring.course.catalog.persistence.repository.course

import com.kotlinspring.course.catalog.model.entity.Course
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

interface CustomCourseRepository {

    fun persistAll(courses: List<Course>): List<Course>

    fun listAll(): List<Course>
}
