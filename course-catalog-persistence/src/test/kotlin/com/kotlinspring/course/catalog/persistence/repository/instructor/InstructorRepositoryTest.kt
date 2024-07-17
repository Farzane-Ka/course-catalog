package com.kotlinspring.course.catalog.persistence.repository.instructor

import com.kotlinspring.course.catalog.model.entity.Instructor
import com.kotlinspring.course.catalog.persistence.PersistenceIntegrationTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.junit.jupiter.api.Assertions.*

class InstructorRepositoryTest : PersistenceIntegrationTest() {

    @Autowired
    lateinit var instructorRepository: InstructorRepository

    @Test
    @Transactional
    fun createInstructor() {
        val result = instructorRepository.save(Instructor(null, "Joe"))
        assertEquals("Joe", result.name)
    }
}
