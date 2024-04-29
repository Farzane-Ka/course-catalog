package com.kotlinspring.course.catalog.controller.greeting.config

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.test.context.junit.jupiter.SpringExtension

@Configuration
@Profile("test")
@ComponentScan("com.kotlinspring.course.catalog")
class ControllerTestConfig
