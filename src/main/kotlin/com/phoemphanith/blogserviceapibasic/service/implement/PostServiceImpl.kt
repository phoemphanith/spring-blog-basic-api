package com.phoemphanith.blogserviceapibasic.service.implement

import com.phoemphanith.blogserviceapibasic.entity.Post
import com.phoemphanith.blogserviceapibasic.payload.PaginateResponse
import com.phoemphanith.blogserviceapibasic.payload.PostDTO
import com.phoemphanith.blogserviceapibasic.repository.PostRepository
import com.phoemphanith.blogserviceapibasic.service.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrDefault
import kotlin.jvm.optionals.getOrElse
import kotlin.jvm.optionals.getOrNull

@Service
class PostServiceImpl: PostService {
    @Autowired
    lateinit var postRepository: PostRepository

    override fun createPost(payload: PostDTO): PostDTO? {
        var newPost = Post(
            title = payload.title,
            description = payload.description,
            content = payload.content
        )
        val post = postRepository.save(newPost)
        return PostDTO(post.id, post.title, post.description, post.content)
    }

    override fun listAllPosts(): List<PostDTO>? {
        return postRepository.findAll().map { PostDTO(it.id, it.title, it.description, it.content) }
    }

    override fun paginationPostList(page: Int, size: Int): PaginateResponse {
        val sort = Sort.by("id").descending()
        val pageParam: Pageable = PageRequest.of(page, size, sort)
        val posts = postRepository.findAll(pageParam)

        return PaginateResponse(
            results = posts.content,
            pageNo = page,
            pageSize = size,
            totalElements = posts.totalElements,
            totalPages = posts.totalPages,
            last = posts.isLast
        )
    }

    override fun showPostDetail(id: Long): PostDTO? {
        val post = postRepository.findById(id).get()
        return PostDTO(post.id, post.title, post.description, post.content)
    }

    override fun updatePost(id: Long, payload: PostDTO): PostDTO? {
        val post = postRepository.findById(id).get()

        post.title = payload.title
        post.description = payload.description
        post.content = payload.content

        val newPost = postRepository.save(post)

        return PostDTO(newPost.id, newPost.title, newPost.description, newPost.content)
    }

    override fun deletePost(id: Long): Any? {
        postRepository.findById(id).orElseThrow() ?: return false
        postRepository.deleteById(id)
        return true
    }
}