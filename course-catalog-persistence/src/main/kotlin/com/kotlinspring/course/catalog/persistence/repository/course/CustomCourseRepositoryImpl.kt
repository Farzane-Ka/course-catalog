package com.kotlinspring.course.catalog.persistence.repository.course

import com.kotlinspring.course.catalog.model.entity.Course
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class CustomCourseRepositoryImpl : CustomCourseRepository {

    @PersistenceContext
    lateinit var entityManager: EntityManager

    @Transactional
    override fun persistAll(courses: List<Course>): List<Course> {
        courses.forEach { entityManager.persist(it) }
        return courses
    }

    override fun listAll(): List<Course> {
        return entityManager.createQuery("SELECT e FROM Course e").resultList as List<Course>
    }
}
