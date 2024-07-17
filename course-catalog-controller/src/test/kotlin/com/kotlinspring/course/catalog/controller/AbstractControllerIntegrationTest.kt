package com.kotlinspring.course.catalog.controller

import com.kotlinspring.course.catalog.controller.config.ControllerTestConfig
import com.kotlinspring.course.catalog.persistence.config.PersistenceTestConfig
import com.kotlinspring.course.catalog.persistence.repository.course.CourseRepository
import com.kotlinspring.course.catalog.service.config.GreetingConfigTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.TestInfo
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@ActiveProfiles("dev")
@Rollback(false)
@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [ControllerTestConfig::class, GreetingConfigTest::class, PersistenceTestConfig::class])
@EntityScan("com.kotlinspring.course.catalog")
@Tag("integration-test")
abstract class AbstractControllerIntegrationTest
