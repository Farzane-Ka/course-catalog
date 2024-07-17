package com.kotlinspring.course.catalog.controller.instructor

import com.kotlinspring.course.catalog.controller.AbstractControllerIntegrationTest
import com.kotlinspring.course.catalog.model.course.CourseDTO
import com.kotlinspring.course.catalog.model.course.InstructorDTO
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
@AutoConfigureWebTestClient
class InstructorControllerIntegrationTest : AbstractControllerIntegrationTest() {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    fun createInstructor() {
        val instructorDto = InstructorDTO(null, "Joe")
        val savedInstructor = webTestClient.post()
            .uri("/v1/instructors")
            .bodyValue(instructorDto)
            .exchange()
            .expectStatus().isCreated
            .expectBody(InstructorDTO::class.java)
            .returnResult()
            .responseBody
        assertTrue {
            savedInstructor!!.instructorId != null
        }
    }
}