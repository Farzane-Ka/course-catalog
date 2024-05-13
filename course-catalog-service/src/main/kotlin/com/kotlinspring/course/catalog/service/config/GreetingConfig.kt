package com.kotlinspring.course.catalog.service.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Profile("default")
@Configuration
@EnableConfigurationProperties(GreetingProperties::class)
class GreetingConfig