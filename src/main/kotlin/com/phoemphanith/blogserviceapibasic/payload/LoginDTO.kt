package com.phoemphanith.blogserviceapibasic.payload

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

class LoginDTO (
    @field:NotBlank
    @field:Email
    var email: String? = null,
    @field:NotBlank
    var password: String? = null
)