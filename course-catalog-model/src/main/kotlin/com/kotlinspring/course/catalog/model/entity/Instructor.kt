package com.kotlinspring.course.catalog.model.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "instructor")
data class Instructor(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "instructor_id")
    val instructorId: UUID?,
    var name: String,
    @OneToMany(
        mappedBy = "instructor",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    var courses: List<Course> = mutableListOf()
)
