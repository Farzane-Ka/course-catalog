package com.kotlinspring.course.catalog.service.greeting

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service


interface GreetingService {
    fun retrieveGreeting(name: String): String
}


@Service
class GreetingServiceImp : GreetingService {

    @Value("\${greeting}")
    lateinit var message: String

    override fun retrieveGreeting(name: String) = "Hello $name, $message"
}
