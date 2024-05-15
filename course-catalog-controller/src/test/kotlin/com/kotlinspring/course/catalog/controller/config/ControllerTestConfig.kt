package com.kotlinspring.course.catalog.controller.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile("dev")
@ComponentScan("com.kotlinspring.course.catalog")
class ControllerTestConfig
