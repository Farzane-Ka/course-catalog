package com.kotlinspring.course.catalog.service.instructor

import com.kotlinspring.course.catalog.model.course.InstructorDTO
import com.kotlinspring.course.catalog.model.entity.Instructor
import java.util.Optional
import java.util.UUID

interface InstructorService {

    fun createInstructor(instructorDTO: InstructorDTO): InstructorDTO

    fun findByInstructorId(instructorId: UUID): Optional<Instructor>
}
