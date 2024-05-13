package com.kotlinspring.course.catalog.service.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Profile("dev")
@Configuration
@ComponentScan("com.kotlinspring.course.catalog.service")
class GreetingConfigTest
