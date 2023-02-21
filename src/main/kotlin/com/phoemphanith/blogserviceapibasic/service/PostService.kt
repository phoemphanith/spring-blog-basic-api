package com.phoemphanith.blogserviceapibasic.service;

import com.phoemphanith.blogserviceapibasic.payload.PaginateResponse
import com.phoemphanith.blogserviceapibasic.payload.PostDTO

interface PostService {
    fun createPost(payload: PostDTO): PostDTO?
    fun listAllPosts(): List<PostDTO>?
    fun paginationPostList(page: Int, size: Int): PaginateResponse
    fun showPostDetail(id: Long): PostDTO?
    fun updatePost(id: Long, payload: PostDTO): PostDTO?
    fun deletePost(id: Long): Any?
}