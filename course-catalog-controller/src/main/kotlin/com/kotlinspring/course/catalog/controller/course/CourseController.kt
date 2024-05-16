package com.kotlinspring.course.catalog.controller.course

import com.kotlinspring.course.catalog.model.course.CourseDTO
import com.kotlinspring.course.catalog.service.course.CourseService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/v1/courses")
@Validated
class CourseController(val courseService: CourseService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addCourse(@RequestBody @Valid course: CourseDTO): CourseDTO = courseService.addCourse(course)

    @GetMapping
    fun retrieveAllCourses(): List<CourseDTO> = courseService.retrieveAllCourses()

    @PutMapping("/{course_id}")
    fun updateCourse(
        @RequestBody courseDTO: CourseDTO,
        @PathVariable("course_id") courseId: UUID
    ) = courseService.updateCourse(courseId, courseDTO)

    @DeleteMapping("/{course_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCourse(@PathVariable("course_id") courseId: UUID) = courseService.deleteCourse(courseId)

}