package com.phoemphanith.blogserviceapibasic.service

import com.phoemphanith.blogserviceapibasic.payload.CommentDTO

interface CommentService {
    fun createComment(postId: Long, payload: CommentDTO): CommentDTO?
    fun listAllComment(postId: Long): List<CommentDTO>
    fun showCommentDetail(postId: Long, commentId: Long): CommentDTO?
    fun updateComment(commentId: Long, postId: Long, payload: CommentDTO): CommentDTO?
    fun deleteComment(commentId: Long): Boolean?
}