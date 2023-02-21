package com.phoemphanith.blogserviceapibasic.controller

import com.phoemphanith.blogserviceapibasic.payload.PaginateResponse
import com.phoemphanith.blogserviceapibasic.payload.PostDTO
import com.phoemphanith.blogserviceapibasic.service.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
@RequestMapping("api/posts")
class PostController {
    @Autowired
    lateinit var postService: PostService

    @PostMapping
    fun create(@RequestBody post: PostDTO) = ResponseEntity<PostDTO>(postService.createPost(post), HttpStatus.CREATED)

    @GetMapping
    fun list() = ResponseEntity<List<PostDTO>>(postService.listAllPosts(), HttpStatus.OK)

    @GetMapping("/list")
    fun paginate(
        @RequestParam(name = "page", defaultValue = "0", required = false) page: Int,
        @RequestParam(name = "size", defaultValue = "10", required = false) size: Int
    ): PaginateResponse{
        return postService.paginationPostList(page, size)
    }

    @GetMapping("/{id}")
    fun detail(@PathVariable id: Long) = ResponseEntity<PostDTO>(postService.showPostDetail(id), HttpStatus.OK)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody post: PostDTO): ResponseEntity<PostDTO>{
        return ResponseEntity<PostDTO>(postService.updatePost(id, post), HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = ResponseEntity<Any>(postService.deletePost(id), HttpStatus.OK)
}