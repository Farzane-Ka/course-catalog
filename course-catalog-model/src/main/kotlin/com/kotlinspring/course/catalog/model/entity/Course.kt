package com.kotlinspring.course.catalog.model.entity

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "course")
data class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "course_id")
    val courseId: UUID?,
    var name: String,
    var category: String,
)
