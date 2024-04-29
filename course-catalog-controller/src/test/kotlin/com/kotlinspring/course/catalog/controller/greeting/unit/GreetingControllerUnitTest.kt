//package com.kotlinspring.course.catalog.controller.greeting.unit
//
//import com.kotlinspring.course.catalog.controller.greeting.GreetingController
//import com.kotlinspring.course.catalog.service.greeting.GreetingService
//import io.mockk.every
//import org.junit.jupiter.api.Assertions
//import org.junit.jupiter.api.Disabled
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.boot.test.mock.mockito.MockBean
//import org.springframework.test.web.reactive.server.WebTestClient
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@WebMvcTest(controllers = [GreetingController::class])
//@AutoConfigureWebTestClient
//class GreetingControllerUnitTest {
//
//    @Autowired
//    lateinit var webTestClient: WebTestClient
//
//    @MockBean
//    lateinit var greetingService: GreetingService
//
//    @Disabled
//    @Test
//    fun retrieveGreeting() {
//        val name = "Farzane"
//        every { greetingService.retrieveGreeting(any()) } returns "Hello $name, greeting from the default profile"
//        val result = webTestClient.get().uri("/v1/greetings/{name}", name)
//            .exchange()
//            .expectStatus().is2xxSuccessful
//            .expectBody(String::class.java)
//            .returnResult()
//        Assertions.assertEquals("Hello $name, greeting from the default profile", result)
//    }
//}
