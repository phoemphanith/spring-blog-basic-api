package com.phoemphanith.blogserviceapibasic.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*
import kotlin.collections.HashMap

@ControllerAdvice
class CustomResponseException: ResponseEntityExceptionHandler() {
    @ExceptionHandler(CustomException::class)
    fun customExceptionHandler(ex: CustomException): ResponseEntity<Any>{
        val body = HashMap<String, Any>()
        body["code"] = ex.status
        body["error"] = ex.error
        body["message"] = ex.localizedMessage
        body["timestamp"] = Date()
        return ResponseEntity(mapOf("response" to body), HttpStatus.OK)
    }
}