package com.phoemphanith.blogserviceapibasic.exception

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*


@ControllerAdvice
class CustomGlobalException: ResponseEntityExceptionHandler() {
    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val body: MutableMap<String, Any> = LinkedHashMap()
        body["timestamp"] = Date()
        body["status"] = status.value()
        body["uri"] = (request as ServletWebRequest).request.requestURI.toString()
        val errors: MutableList<String> = ArrayList()
        for (error in ex.bindingResult.fieldErrors){
            errors.add("${error.field}:${error.defaultMessage}")
        }
        for (error in ex.bindingResult.globalErrors){
            errors.add("${error.objectName}:${error.defaultMessage}")
        }
        body["errors"] = errors
        return ResponseEntity(body, headers, status)
    }
}