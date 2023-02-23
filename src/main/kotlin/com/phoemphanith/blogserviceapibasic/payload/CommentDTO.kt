package com.phoemphanith.blogserviceapibasic.payload

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

class CommentDTO (
    var id: Long? = null,
    @field:NotEmpty
    var name: String? = null,
    @field:NotEmpty
    @field:Email
    var email: String? = null,
    @field:Size(min = 10, message = "must be have at least 10 character")
    var body: String? = null
)