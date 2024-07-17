package com.kotlinspring.course.catalog.persistence.repository.course

import com.kotlinspring.course.catalog.model.entity.Course
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CourseRepository : JpaRepository<Course, UUID> {

    @Query(value = "SELECT * from Course where name like %?1%", nativeQuery = true)
    fun findByName(name: String): List<Course>
}
