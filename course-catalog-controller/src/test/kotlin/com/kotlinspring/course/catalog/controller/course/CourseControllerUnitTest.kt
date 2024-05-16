package com.kotlinspring.course.catalog.controller.course

import com.kotlinspring.course.catalog.controller.config.ControllerTestConfig
import com.kotlinspring.course.catalog.controller.util.courseDTO
import com.kotlinspring.course.catalog.controller.util.courseDTOList
import com.kotlinspring.course.catalog.model.course.CourseDTO
import com.kotlinspring.course.catalog.service.course.CourseService
import com.ninjasquad.springmockk.MockkBean
import io.github.atomfinger.touuid.toUUID
import io.mockk.every
import io.mockk.just
import io.mockk.runs
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
import java.lang.RuntimeException

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
        val courseDTO = CourseDTO(
            null,
            "Build Restful APIs using SpringBoot and Kotlin",
            "Development course")

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

        assertTrue {
            savedCourseDTO!!.courseId != null
        }
    }

    @Test
    fun validationForAddCourse() {
        val courseDTO = CourseDTO(null, "","")

        every { courseServiceMockk.addCourse(any()) } returns courseDTO(1.toUUID())

        val response = webTestClient
            .post()
            .uri("/v1/courses")
            .bodyValue(courseDTO)
            .exchange()
            .expectStatus().isBadRequest
            .expectBody(String::class.java)
            .returnResult()
            .responseBody
        assertEquals("Course category cannot be blank, Course name cannot be blank", response)
    }

    @Test
    fun retrieveAllCourses() {
        every { courseServiceMockk.retrieveAllCourses() } returns courseDTOList()

        val coursDtos = webTestClient
            .get()
            .uri("/v1/courses")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(CourseDTO::class.java)
            .returnResult()
            .responseBody

        assertEquals(2, coursDtos!!.size)
    }

    @Test
    fun updateCourse() {
        every {
            courseServiceMockk.updateCourse(any(), any())
        } returns CourseDTO(1.toUUID(), "math2", "Mathematics")

        val courseDTO = CourseDTO(null, "math2", "Mathematics")
        val updatedCourse = webTestClient
            .put()
            .uri("/v1/courses/{course_id}", 1.toUUID())
            .bodyValue(courseDTO)
            .exchange()
            .expectStatus().isOk
            .expectBody(CourseDTO::class.java)
            .returnResult()
            .responseBody
        assertEquals("math2", updatedCourse!!.name)
    }

    @Test
    fun deleteCourse() {
        every { courseServiceMockk.deleteCourse(any()) } just runs

        webTestClient
            .delete()
            .uri("/v1/courses/{course_id}", 1.toUUID())
            .exchange()
            .expectStatus().isNoContent
    }

    @Test
    fun runtimeException() {
        val courseDTO = CourseDTO(null, "math2", "Mathematics")

        val errorMessage= "Unexpected Error occurred"
        every { courseServiceMockk.addCourse(any()) } throws  RuntimeException(errorMessage)

        val response = webTestClient
            .post()
            .uri("/v1/courses")
            .bodyValue(courseDTO)
            .exchange()
            .expectStatus().is5xxServerError
            .expectBody(String::class.java)
            .returnResult()
            .responseBody
        assertEquals("Unexpected Error occurred", response)
    }
}