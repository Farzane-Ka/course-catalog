package com.kotlinspring.course.catalog.model.exceptionhandler

import mu.KLogging
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.Exception

@Component
@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    companion object : KLogging()

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val errors = ex.
        bindingResult.
        allErrors.
            map { error -> error.defaultMessage!! }
            .sorted()

        logger.info("the list errors $errors")
        return ResponseEntity.
        status(HttpStatus.BAD_REQUEST).
        body(errors.joinToString(", "))
    }

    @ExceptionHandler(Exception::class)
    fun handleAllException(ex: Exception, webRequest: WebRequest): ResponseEntity<Any> {
        logger.error("Exception observed : ${ex.message}", ex)
        return ResponseEntity.
        status(HttpStatus.INTERNAL_SERVER_ERROR).
        body(ex.message)
    }
}
