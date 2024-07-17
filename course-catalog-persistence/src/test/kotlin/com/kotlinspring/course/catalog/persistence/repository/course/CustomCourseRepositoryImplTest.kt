package com.kotlinspring.course.catalog.persistence.repository.course

import com.kotlinspring.course.catalog.persistence.PersistenceIntegrationTest
import com.kotlinspring.course.catalog.persistence.dataset.courseList
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class CustomCourseRepositoryImplTest : PersistenceIntegrationTest(){

    @Autowired
    lateinit var customCourseRepository: CustomCourseRepository

    @Autowired
    lateinit var courseRepository: CourseRepository

    @BeforeEach
    fun setUp() {

        courseRepository.deleteAll()
        val courses = courseList()
        courseRepository.saveAll(courses)
    }


    @Test
    fun persisAllTest() {
        val result = customCourseRepository.persistAll(courseList())
        assertEquals(3, result.size)
    }

    @Test
    fun findByName() {
        val result = courseRepository.findByName("math1")
        assertEquals(1, result.size)
    }
}