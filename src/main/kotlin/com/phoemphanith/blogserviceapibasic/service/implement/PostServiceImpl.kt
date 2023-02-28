package com.phoemphanith.blogserviceapibasic.service.implement

import com.phoemphanith.blogserviceapibasic.entity.Category
import com.phoemphanith.blogserviceapibasic.entity.Post
import com.phoemphanith.blogserviceapibasic.exception.CustomException
import com.phoemphanith.blogserviceapibasic.payload.response.PaginateResponse
import com.phoemphanith.blogserviceapibasic.payload.PostDTO
import com.phoemphanith.blogserviceapibasic.repository.PostRepository
import com.phoemphanith.blogserviceapibasic.service.PostService
import com.phoemphanith.blogserviceapibasic.payload.enumerate.HttpCode
import com.phoemphanith.blogserviceapibasic.repository.CategoryRepository
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class PostServiceImpl: PostService {
    @Autowired
    lateinit var postRepository: PostRepository
    @Autowired
    lateinit var categoryRepository: CategoryRepository
    val mapper: ModelMapper = ModelMapper()

    override fun createPost(payload: PostDTO): PostDTO? {
        var newPost = Post(
            title = payload.title,
            description = payload.description,
            content = payload.content
        )

        if(payload.categoryId != null){
            val category = categoryRepository.findById(payload.categoryId!!).orElse(null)
                ?: throw CustomException(HttpCode.NOT_FOUND, "Category given by id ${payload.categoryId} not found")
            newPost.category = category
        }

        val post = postRepository.save(newPost)

        return mapper.map(post, PostDTO::class.java)
    }

    override fun listAllPosts(): List<PostDTO> {
        return postRepository.findAll().map { mapper.map(it, PostDTO::class.java) }
    }

    override fun paginationPostList(page: Int, size: Int): Page<PostDTO> {
        val sort = Sort.by("id").descending()
        val pageParam: Pageable = PageRequest.of(page, size, sort)
        return postRepository.findAll(pageParam).map { mapper.map(it, PostDTO::class.java) }
    }

    override fun showPostDetail(id: Long): PostDTO? {
        val post = postRepository.findByIdOrNull(id)
            ?: throw CustomException(HttpCode.NOT_FOUND, "Post given by $id was not found.")
        return mapper.map(post, PostDTO::class.java)
    }

    override fun updatePost(id: Long, payload: PostDTO): PostDTO? {
        return try {
            val post = postRepository.findById(id).get()
            val newPost = Post()

            newPost.title = payload.title
            newPost.description = payload.description
            newPost.content = payload.content

            if(payload.categoryId != null){
                val category = categoryRepository.findById(payload.categoryId!!).orElse(null)
                    ?: throw CustomException(HttpCode.NOT_FOUND, "Category given by id ${payload.categoryId} not found")
                newPost.category = category
            }
            
            mapper.map(postRepository.save(newPost), PostDTO::class.java)
        }catch (ex: RuntimeException){
            throw CustomException(HttpCode.BAD_REQUEST, ex.localizedMessage)
        }
    }

    override fun deletePost(id: Long): Any? {
        return try {
            postRepository.deleteById(id)
            1
        }catch (ex: RuntimeException){
            throw CustomException(HttpCode.NOT_FOUND, ex.message.toString())
        }
    }
}