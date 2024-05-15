package com.kotlinspring.course.catalog.service.course

import com.kotlinspring.course.catalog.model.course.CourseDTO
import java.util.UUID

interface CourseService {

    fun addCourse(course: CourseDTO): CourseDTO

    fun retrieveAllCourses(): List<CourseDTO>

    fun updateCourse(courseId: UUID, courseDTO: CourseDTO): Any

    fun deleteCourse(courseId: UUID)
}
