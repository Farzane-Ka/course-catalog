package com.kotlinspring.course.catalog.controller.greeting

import com.kotlinspring.course.catalog.controller.AbstractControllerIntegrationTest
import com.kotlinspring.course.catalog.controller.config.ControllerTestConfig
import com.kotlinspring.course.catalog.service.config.GreetingConfigTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@EnableAutoConfiguration
@ExtendWith(SpringExtension::class)
class GreetingControllerIntegrationTest : AbstractControllerIntegrationTest() {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    fun retrieveGreeting() {
        val name = "Farzane"
        val result = webTestClient.get()
            .uri("/v1/greetings/{name}", name)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody(String::class.java)
            .returnResult()
        assertEquals("Hello $name, greeting from the default profile", result.responseBody)
    }
}
