package com.kotlinspring.course.catalog.persistence

import com.kotlinspring.course.catalog.persistence.config.PersistenceTestConfig
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(classes = [PersistenceTestConfig::class])
open class PersistenceIntegrationTest