package com.kotlinspring.course.catalog.service.instructor

import com.kotlinspring.course.catalog.model.course.InstructorDTO
import com.kotlinspring.course.catalog.model.entity.Instructor
import com.kotlinspring.course.catalog.persistence.repository.instructor.InstructorRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class InstructorServiceImpl(val instructorRepository: InstructorRepository) : InstructorService {
    override fun createInstructor(instructorDTO: InstructorDTO): InstructorDTO {
        val instructorEntity = instructorDTO.let {
            Instructor(
                instructorId = it.instructorId,
                name = it.name
            )
        }
        instructorRepository.save(instructorEntity)
        return instructorEntity.let {
            InstructorDTO(
                instructorId = it.instructorId,
                name = it.name
            )
        }
    }

    override fun findByInstructorId(instructorId: UUID): Optional<Instructor> {
        return instructorRepository.findById(instructorId)
    }
}
