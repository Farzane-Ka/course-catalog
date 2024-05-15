package com.kotlinspring.course.catalog.controller.course

import com.kotlinspring.course.catalog.controller.config.ControllerTestConfig
import com.kotlinspring.course.catalog.controller.util.courseDTO
import com.kotlinspring.course.catalog.model.course.CourseDTO
import com.kotlinspring.course.catalog.service.course.CourseService
import com.ninjasquad.springmockk.MockkBean
import io.github.atomfinger.touuid.toUUID
import io.mockk.every
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@WebMvcTest(controllers = [CourseController::class])
@AutoConfigureWebTestClient
@ActiveProfiles("dev")
@Rollback(false)
@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [ControllerTestConfig::class])
class CourseControllerUnitTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var courseServiceMockk : CourseService

    @Test
    fun addCourse() {

        val courseDTO = CourseDTO(null, "Build Restful APIs using SpringBoot and Kotlin"
            ,"Development course")

        every { courseServiceMockk.addCourse(any()) } returns courseDTO(1.toUUID())

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

//    @Test
//    fun retrieveAllCourses() {
//        val coursDtos = webTestClient
//            .get()
//            .uri("/v1/courses")
//            .exchange()
//            .expectStatus().isOk
//            .expectBodyList(CourseDTO::class.java)
//            .returnResult()
//            .responseBody
//
//        assertEquals(2, coursDtos!!.size)
//    }
//
//    @Test
//    fun updateCourse() {
//        val course = Course(null, "math1", "Mathematics")
//        repository.save(course)
//        val courseDtO = CourseDTO(null, "math2", "Mathematics")
//        val updatedCourse = webTestClient
//            .put()
//            .uri("/v1/courses/{course_id}", course.courseId)
//            .bodyValue(courseDtO)
//            .exchange()
//            .expectStatus().isOk
//            .expectBody(CourseDTO::class.java)
//            .returnResult()
//            .responseBody
//        assertEquals("math2", updatedCourse!!.name)
//    }
//
//    @Test
//    fun deleteCourse() {
//        val course = Course(null, "math1", "Mathematics")
//        repository.save(course)
//        webTestClient
//            .delete()
//            .uri("/v1/courses/{course_id}", course.courseId)
//            .exchange()
//            .expectStatus().isNoContent
//    }
}