package com.phoemphanith.blogserviceapibasic.payload.response

class PaginateResponse(
    var results: List<Any?>? = emptyList(),
    var pageNo: Int? = 0,
    var pageSize: Int? = 0,
    var totalElements: Long? = 0,
    var totalPages: Int? = 0,
    var last: Boolean? = false
)