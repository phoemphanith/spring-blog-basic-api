package com.phoemphanith.blogserviceapibasic.controller

import com.phoemphanith.blogserviceapibasic.payload.CommentDTO
import com.phoemphanith.blogserviceapibasic.service.CommentService
import com.phoemphanith.blogserviceapibasic.utils.AppConstant
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

    @PostMapping
    fun create(@PathVariable postId: Long, @RequestBody payload: CommentDTO): ResponseEntity<CommentDTO>{
        return ResponseEntity.ok().body(commentService.createComment(postId, payload))
    }

    @GetMapping
    fun list(@PathVariable postId: Long): ResponseEntity<List<CommentDTO>>{
        return ResponseEntity.ok().body(commentService.listAllComment(postId))
    }

    @GetMapping("/{commentId}")
    fun show(@PathVariable postId: Long, @PathVariable commentId: Long): ResponseEntity<CommentDTO>{
        return ResponseEntity.ok().body(commentService.showCommentDetail(postId, commentId))
    }

    @PutMapping("/{commentId}")
    fun update(
        @PathVariable postId: Long,
        @PathVariable commentId: Long,
        @RequestBody commentDTO: CommentDTO
    ): ResponseEntity<CommentDTO>{
        return ResponseEntity.ok().body(commentService.updateComment(commentId, postId, commentDTO))
    }

    @DeleteMapping("/{commentId}")
    fun delete(
        @PathVariable postId: Int,
        @PathVariable commentId: Long,
    ): ResponseEntity<Boolean>{
        return ResponseEntity.ok().body(commentService.deleteComment(commentId))
    }
}