package com.kotlinspring.course.catalog.controller.course

import com.kotlinspring.course.catalog.controller.AbstractControllerIntegrationTest
import com.kotlinspring.course.catalog.controller.util.courseList
import com.kotlinspring.course.catalog.model.course.CourseDTO
import com.kotlinspring.course.catalog.model.entity.Course
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
@AutoConfigureWebTestClient
class CourseControllerIntegrationTest : AbstractControllerIntegrationTest() {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    fun addCourse() {

        val courseDTO = CourseDTO(null, "Build Restful APIs using SpringBoot and Kotlin"
            ,"Development course")

        val savedCourseDTO = webTestClient
            .post()
            .uri("/v1/courses")
            .bodyValue(courseDTO)
            .exchange()
            .expectStatus().isCreated
            .expectBody(CourseDTO::class.java)
            .returnResult()
            .responseBody

        Assertions.assertTrue {
            savedCourseDTO!!.courseId != null
        }
    }

    @Test
    fun retrieveAllCourses() {
        repository.saveAll(courseList())
        val courseDtos = webTestClient
            .get()
            .uri("/v1/courses")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(CourseDTO::class.java)
            .returnResult()
            .responseBody

        assertEquals(2, courseDtos!!.size)
    }

    @Test
    fun updateCourse() {
        val course = Course(null, "math1", "Mathematics")
        repository.save(course)
        val courseDtO = CourseDTO(null, "math2", "Mathematics")
        val updatedCourse = webTestClient
            .put()
            .uri("/v1/courses/{course_id}", course.courseId)
            .bodyValue(courseDtO)
            .exchange()
            .expectStatus().isOk
            .expectBody(CourseDTO::class.java)
            .returnResult()
            .responseBody
        assertEquals("math2", updatedCourse!!.name)
    }

    @Test
    fun deleteCourse() {
        val course = Course(null, "math1", "Mathematics")
        repository.save(course)
        webTestClient
            .delete()
            .uri("/v1/courses/{course_id}", course.courseId)
            .exchange()
            .expectStatus().isNoContent
    }
}
