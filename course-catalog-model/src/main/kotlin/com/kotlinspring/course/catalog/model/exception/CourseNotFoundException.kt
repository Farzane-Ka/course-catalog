package com.kotlinspring.course.catalog.model.exception

import java.lang.RuntimeException

class CourseNotFoundException(message: String) : RuntimeException(message)