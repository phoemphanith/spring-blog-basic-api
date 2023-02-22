package com.phoemphanith.blogserviceapibasic.payload.response

import com.phoemphanith.blogserviceapibasic.entity.Post
import com.phoemphanith.blogserviceapibasic.payload.PostDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
class ResponseObjectMap {
    @Autowired
    lateinit var response: ResponseObject

    fun body(obj: Any?): MutableMap<String, Any?> {
        val result: MutableMap<String, Any?> = HashMap()
        result["result"] = obj
        result += if(obj == null){
            response.error()
        }else{
            response.success()
        }
        return result
    }

    fun list(obj: List<Any>): MutableMap<String, Any>{
        val result: MutableMap<String, Any> = HashMap()
        result["result"] = obj
        result["totalElements"] = obj.size
        result += response.success()
        return result
    }

    fun paginate(obj: Page<out Any>, page: Int, size: Int): MutableMap<String, Any>{
        val result: MutableMap<String, Any> = HashMap()
        result.putAll(response.success())
        result["first"] = obj.isFirst
        result["last"] = obj.isLast
        result["currentPage"] = page
        result["size"] = size
        result["totalElements"] = obj.totalElements
        result["totalPages"] = obj.totalPages
        result["result"] = obj.content
        return result
    }
}