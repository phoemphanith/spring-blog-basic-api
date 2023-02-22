package com.phoemphanith.blogserviceapibasic.payload.enumerate

enum class HttpCode(val code: Int, val reasonPhrase: String) {
    SUCCESS(200, "Success"),
    ERROR(500, "Internal Server Error"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    NOT_ACCEPTABLE(406, "Not Acceptable");
}