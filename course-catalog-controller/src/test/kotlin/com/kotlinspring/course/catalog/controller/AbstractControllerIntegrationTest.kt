package com.kotlinspring.course.catalog.controller

import com.kotlinspring.course.catalog.controller.config.ControllerTestConfig
import com.kotlinspring.course.catalog.persistence.repository.CourseRepository
import com.kotlinspring.course.catalog.service.config.GreetingConfigTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.TestInfo
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@ActiveProfiles("dev")
@Rollback(false)
@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [ControllerTestConfig::class, GreetingConfigTest::class])
@EnableJpaRepositories("com.kotlinspring.course.catalog")
@EntityScan("com.kotlinspring.course.catalog")
@Tag("integration-test")
abstract class AbstractControllerIntegrationTest {

    @Autowired
    lateinit var repository: CourseRepository

    @BeforeEach
    fun before(testInfo: TestInfo) {
        repository.deleteAll()
        }
}
