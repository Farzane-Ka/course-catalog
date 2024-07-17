package com.kotlinspring.course.catalog.persistence

import com.kotlinspring.course.catalog.persistence.config.PersistenceTestConfig
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ContextConfiguration(classes = [PersistenceTestConfig::class])
@Tag("integration-test")
@ActiveProfiles("dev")
@Rollback(false)
@ExtendWith(SpringExtension::class)
open class PersistenceIntegrationTest