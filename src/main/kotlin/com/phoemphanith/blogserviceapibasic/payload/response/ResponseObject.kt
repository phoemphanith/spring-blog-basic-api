package com.phoemphanith.blogserviceapibasic.payload.response

import com.phoemphanith.blogserviceapibasic.payload.enumerate.HttpCode
import org.springframework.stereotype.Component

@Component
class ResponseObject(var code: Int? = 0, var message: String? = null) {
    fun success(): MutableMap<String, ResponseObject> {
        val status = HttpCode.SUCCESS
        return mutableMapOf("response" to ResponseObject(status.code, status.reasonPhrase))
    }
    fun error(): MutableMap<String, ResponseObject> {
        val status = HttpCode.NOT_FOUND
        return mutableMapOf("response" to ResponseObject(status.code, status.reasonPhrase))
    }
}