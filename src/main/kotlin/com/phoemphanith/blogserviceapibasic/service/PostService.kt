package com.phoemphanith.blogserviceapibasic.service;

import com.phoemphanith.blogserviceapibasic.entity.Post
import com.phoemphanith.blogserviceapibasic.payload.response.PaginateResponse
import com.phoemphanith.blogserviceapibasic.payload.PostDTO
import org.springframework.data.domain.Page

interface PostService {
    fun createPost(payload: PostDTO): PostDTO?
    fun listAllPosts(): List<PostDTO>
    fun paginationPostList(page: Int, size: Int): Page<PostDTO>
    fun showPostDetail(id: Long): PostDTO?
    fun updatePost(id: Long, payload: PostDTO): PostDTO?
    fun deletePost(id: Long): Any?
}