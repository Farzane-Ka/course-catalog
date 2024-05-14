package com.kotlinspring.course.catalog.controller.greeting

import com.kotlinspring.course.catalog.service.greeting.GreetingService
import mu.KLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/greetings")
class GreetingController(val greetingService: GreetingService) {

    companion object: KLogging()

    @GetMapping("/{name}")
    fun retrieveGreeting(@PathVariable("name") name: String): String {
        logger.info("name is $name")
        greetingService.listAll()
        return greetingService.retrieveGreeting(name)
    }

    @GetMapping
    fun retrieveGreeting(): String {
        return "Hello"
    }
}
