package com.kotlinspring.course.catalog.service.greeting

interface GreetingService {
    fun retrieveGreeting(name: String): String
}
