package com.kotlinspring.course.catalog.persistence.repository

import com.kotlinspring.course.catalog.persistence.PersistenceIntegrationTest
import com.kotlinspring.course.catalog.persistence.dataset.courseList
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(classes = [PersistenceIntegrationTest::class])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class CustomCourseRepositoryImplTest : PersistenceIntegrationTest(){

    @Autowired
    lateinit var customCourseRepository: CustomCourseRepository

    @Test
    fun persisAllTest() {
        val result = customCourseRepository.persistAll(courseList())
        println( customCourseRepository.listAll())
        assertEquals(3, result.size)
    }
}