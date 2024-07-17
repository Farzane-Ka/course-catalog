package com.kotlinspring.course.catalog.persistence.repository.instructor

import com.kotlinspring.course.catalog.model.entity.Instructor
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface InstructorRepository : JpaRepository<Instructor, UUID>
