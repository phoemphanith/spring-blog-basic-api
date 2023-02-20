package com.phoemphanith.blogserviceapibasic.payload

class PostDTO(
    var id: Long? = null,
    var title: String? = null,
    var description: String? = null,
    var content: String? = null
)