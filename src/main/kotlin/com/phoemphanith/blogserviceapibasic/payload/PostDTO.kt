package com.phoemphanith.blogserviceapibasic.payload

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

class PostDTO(
    var id: Long? = null,
    @field:NotEmpty
    var title: String? = null,
    @field:Size(min = 10, message = "Description must have at least 10 character")
    var description: String? = null,
    @field:Size(min = 10, message = "Content must have at least 10 character")
    var content: String? = null
)