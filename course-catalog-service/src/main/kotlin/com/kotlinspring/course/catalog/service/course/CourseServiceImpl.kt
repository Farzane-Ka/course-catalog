package com.kotlinspring.course.catalog.service.course

import com.kotlinspring.course.catalog.model.course.CourseDTO
import com.kotlinspring.course.catalog.model.entity.Course
import com.kotlinspring.course.catalog.model.exception.CourseNotFoundException
import com.kotlinspring.course.catalog.model.exception.InstructorNotValidException
import com.kotlinspring.course.catalog.persistence.repository.course.CourseRepository
import com.kotlinspring.course.catalog.service.instructor.InstructorService
import mu.KLogging
import org.springframework.stereotype.Service
import java.util.*

@Service
class CourseServiceImpl(
    val repository: CourseRepository,
    val instructorService: InstructorService,
    ) : CourseService {

    companion object : KLogging()

    override fun addCourse(courseDTO: CourseDTO): CourseDTO {
        val optionalInstructor = instructorService.findByInstructorId(courseDTO.instructorId!!)
        if (!optionalInstructor.isPresent)
            throw InstructorNotValidException("InstructorId: ${courseDTO.instructorId} is not valid")
        val courseEntity = courseDTO.let {
            Course(
                courseId = null,
                name = it.name,
                category = it.category,
                instructor = optionalInstructor.get(),
            )
        }
        repository.save(courseEntity)
        logger.info("saved object course $courseEntity")
        return courseEntity.let {
            CourseDTO(
                courseId = it.courseId,
                name = it.name,
                category = it.category,
                instructorId = it.instructor!!.instructorId
            )
        }
    }

    override fun retrieveAllCourses(): List<CourseDTO> {
        return repository.findAll().map {
            CourseDTO(
                courseId = it.courseId,
                name = it.name,
                category = it.category,
            )
        }
    }

    override fun updateCourse(courseId: UUID, courseDTO: CourseDTO): Any {
        val existingCourse = repository.findById(courseId)
        return if (existingCourse.isPresent) {
            existingCourse.get().let {
                it.name = courseDTO.name
                it.category = courseDTO.category
                repository.save(it)
                CourseDTO(it.courseId, it.name, it.category)
            }
        } else {
            CourseNotFoundException("No course is found for courseId $courseId")
        }
    }

    override fun deleteCourse(courseId: UUID) {
        val course = repository.findById(courseId)
        if (course.isPresent) {
            repository.deleteById(courseId)
        } else {
            CourseNotFoundException("No course is found for courseId $courseId")
        }
    }
}
