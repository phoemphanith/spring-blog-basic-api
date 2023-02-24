package com.phoemphanith.blogserviceapibasic.payload

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

class RegisterDTO (
    var id: Long? = null,
    @field:NotBlank
    @field:Email
    var email: String? = null,
    @field:NotBlank
    var name: String? = null,
    @field:NotBlank
    @field:Size(max = 10)
    var username: String? = null,
    @field:Size(min = 6)
    var password: String? = null
)