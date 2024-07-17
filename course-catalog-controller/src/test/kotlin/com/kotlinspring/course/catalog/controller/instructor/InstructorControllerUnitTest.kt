package com.kotlinspring.course.catalog.controller.instructor

import com.kotlinspring.course.catalog.controller.config.ControllerTestConfig
import com.kotlinspring.course.catalog.controller.course.CourseController
import com.kotlinspring.course.catalog.controller.util.courseDTO
import com.kotlinspring.course.catalog.model.course.CourseDTO
import com.kotlinspring.course.catalog.model.course.InstructorDTO
import com.kotlinspring.course.catalog.service.instructor.InstructorService
import com.ninjasquad.springmockk.MockkBean
import io.github.atomfinger.touuid.toUUID
import io.mockk.every
import org.junit.jupiter.api.Assertions
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
class InstructorControllerUnitTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var instructorService: InstructorService

    @Test
    fun createInstructor() {
        val instructorDto = InstructorDTO(null, "Joe")
        every { instructorService.createInstructor(any()) } returns InstructorDTO(1.toUUID(), "Joe")
        val savedInstructorDto = webTestClient
            .post()
            .uri("/v1/instructors")
            .bodyValue(instructorDto)
            .exchange()
            .expectStatus().isCreated
            .expectBody(InstructorDTO::class.java)
            .returnResult()
            .responseBody
        Assertions.assertTrue {
            savedInstructorDto!!.instructorId != null
        }
    }
}
