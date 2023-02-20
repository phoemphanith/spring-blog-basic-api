package com.phoemphanith.blogserviceapibasic.repository

import com.phoemphanith.blogserviceapibasic.entity.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository: JpaRepository<Post, Long> {
}