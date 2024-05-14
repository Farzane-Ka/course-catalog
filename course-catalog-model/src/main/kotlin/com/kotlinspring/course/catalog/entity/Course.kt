package com.kotlinspring.course.catalog.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "course")
data class Course(
    @Id
    val courseId: UUID,
    val name: String,
    val category: String,
)
