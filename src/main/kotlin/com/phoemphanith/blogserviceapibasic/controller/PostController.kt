package com.phoemphanith.blogserviceapibasic.controller

import com.phoemphanith.blogserviceapibasic.payload.PostDTO
import com.phoemphanith.blogserviceapibasic.payload.response.ResponseObjectMap
import com.phoemphanith.blogserviceapibasic.service.PostService
import com.phoemphanith.blogserviceapibasic.utils.AppConstant
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(AppConstant.MAIN_ENDPOINT + "/posts")
class PostController {
    @Autowired
    lateinit var postService: PostService
    @Autowired
    lateinit var response: ResponseObjectMap

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    fun create(@Valid @RequestBody post: PostDTO): MutableMap<String, Any?> {
        return response.body(postService.createPost(post))
    }

    @GetMapping
    fun list() = response.list(postService.listAllPosts())

    @GetMapping("/list")
    fun paginate(
        @RequestParam(name = "page", defaultValue = "0", required = false) page: Int,
        @RequestParam(name = "size", defaultValue = "10", required = false) size: Int
    ): MutableMap<String, Any> {
        return response.paginate(postService.paginationPostList(page, size), page, size)
    }

    @GetMapping("/{id}")
    fun detail(@PathVariable id: Long) = response.body(postService.showPostDetail(id))

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody post: PostDTO
    ): MutableMap<String, Any?>{
        return response.body(postService.updatePost(id, post))
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = response.body(postService.deletePost(id))
}