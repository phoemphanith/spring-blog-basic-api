package com.phoemphanith.blogserviceapibasic.exception

import com.phoemphanith.blogserviceapibasic.payload.enumerate.HttpCode

class CustomException(status: HttpCode, message: String): RuntimeException(message) {
    var status = status.code
    var error = status.reasonPhrase
}