package com.kotlinspring.course.catalog.persistence.repository

import com.kotlinspring.course.catalog.model.entity.Course
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CourseRepository : JpaRepository<Course, UUID> {

    fun findAllBy(): List<Course>

    fun findAllByCourseId(courseId: UUID): List<Course>
}
