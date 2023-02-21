package com.phoemphanith.blogserviceapibasic.repository

import com.phoemphanith.blogserviceapibasic.entity.Comment
import com.phoemphanith.blogserviceapibasic.entity.Post
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query


interface CommentRepository: JpaRepository<Comment, Long> {
    fun findAllByPostIdOrderByIdDesc(postId: Long): List<Comment>
    fun findByIdAndPostId(id: Long, postId: Long): Comment?
}