package com.kotlinspring.course.catalog.controller.course

import com.kotlinspring.course.catalog.controller.AbstractControllerIntegrationTest
import com.kotlinspring.course.catalog.controller.util.courseEntityList
import com.kotlinspring.course.catalog.controller.util.instructorEntity
import com.kotlinspring.course.catalog.model.course.CourseDTO
import com.kotlinspring.course.catalog.model.entity.Course
import com.kotlinspring.course.catalog.persistence.repository.course.CourseRepository
import com.kotlinspring.course.catalog.persistence.repository.instructor.InstructorRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
@AutoConfigureWebTestClient
class CourseControllerIntegrationTest : AbstractControllerIntegrationTest() {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var instructorRepository: InstructorRepository

    @Autowired
    lateinit var courseRepository: CourseRepository

    @BeforeEach
    fun setUp() {

        courseRepository.deleteAll()
        instructorRepository.deleteAll()

        val instructor = instructorEntity()
        instructorRepository.save(instructor)

        val courses = courseEntityList(instructor)
        courseRepository.saveAll(courses)
    }

    @Test
    fun addCourse() {
        val instructor = instructorRepository.findAll().first()
        val courseDTO = CourseDTO(
            null,
            "Build Restful APIs using SpringBoot and Kotlin",
            "Development course",
            instructor.instructorId,
        )
        val savedCourseDTO = webTestClient
            .post()
            .uri("/v1/courses")
            .bodyValue(courseDTO)
            .exchange()
            .expectStatus().isCreated
            .expectBody(CourseDTO::class.java)
            .returnResult()
            .responseBody
        assertTrue {
            savedCourseDTO!!.courseId != null
        }
    }

    @Test
    fun retrieveAllCourses() {
        val courseDtos = webTestClient
            .get()
            .uri("/v1/courses")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(CourseDTO::class.java)
            .returnResult()
            .responseBody
        assertEquals(3, courseDtos!!.size)
    }

    @Test
    fun updateCourse() {
        val instructor = instructorRepository.findAll().first()
        val course = Course(null, "math1", "Mathematics", instructor)
        courseRepository.save(course)
        val courseDto = CourseDTO(null, "math2", "Mathematics", instructor.instructorId)
        val updatedCourse = webTestClient
            .put()
            .uri("/v1/courses/{course_id}", course.courseId)
            .bodyValue(courseDto)
            .exchange()
            .expectStatus().isOk
            .expectBody(CourseDTO::class.java)
            .returnResult()
            .responseBody
        assertEquals("math2", updatedCourse!!.name)
    }

    @Test
    fun deleteCourse() {
        val instructor = instructorRepository.findAll().first()
        val course = Course(null, "math1", "Mathematics", instructor)
        courseRepository.save(course)
        webTestClient
            .delete()
            .uri("/v1/courses/{course_id}", course.courseId)
            .exchange()
            .expectStatus().isNoContent
    }
}
