package com.phoemphanith.blogserviceapibasic.controller

import com.phoemphanith.blogserviceapibasic.payload.CommentDTO
import com.phoemphanith.blogserviceapibasic.payload.response.ResponseObjectMap
import com.phoemphanith.blogserviceapibasic.service.CommentService
import com.phoemphanith.blogserviceapibasic.utils.AppConstant
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(AppConstant.MAIN_ENDPOINT + "/posts/{postId}/comments")
class CommentController {
    @Autowired
    lateinit var commentService: CommentService
    @Autowired
    lateinit var response: ResponseObjectMap

    @PostMapping
    fun create(
        @PathVariable postId: Long,
        @Valid @RequestBody payload: CommentDTO
    ): MutableMap<String, Any?>{
        return response.body(commentService.createComment(postId, payload))
    }

    @GetMapping
    fun list(@PathVariable postId: Long): MutableMap<String, Any>{
        return response.list(commentService.listAllComment(postId))
    }

    @GetMapping("/{commentId}")
    fun show(
        @PathVariable postId: Long,
        @PathVariable commentId: Long
    ): MutableMap<String, Any?>{
        return response.body(commentService.showCommentDetail(postId, commentId))
    }

    @PutMapping("/{commentId}")
    fun update(
        @PathVariable postId: Long,
        @PathVariable commentId: Long,
        @Valid @RequestBody commentDTO: CommentDTO
    ): MutableMap<String, Any?>{
        return response.body(commentService.updateComment(commentId, postId, commentDTO))
    }

    @DeleteMapping("/{commentId}")
    fun delete(
        @PathVariable postId: Int,
        @PathVariable commentId: Long,
    ): MutableMap<String, Any?>{
        return response.body(commentService.deleteComment(commentId))
    }
}