package com.phoemphanith.blogserviceapibasic.service.implement

import com.phoemphanith.blogserviceapibasic.entity.Comment
import com.phoemphanith.blogserviceapibasic.payload.CommentDTO
import com.phoemphanith.blogserviceapibasic.repository.CommentRepository
import com.phoemphanith.blogserviceapibasic.repository.PostRepository
import com.phoemphanith.blogserviceapibasic.service.CommentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl: CommentService {

    @Autowired
    lateinit var postRepository: PostRepository
    @Autowired
    lateinit var commentRepository: CommentRepository

    override fun createComment(postId: Long, payload: CommentDTO): CommentDTO? {
        val post= postRepository.findById(postId).orElse(null)
        if (post == null) return post
        val newComment = Comment()
        newComment.name = payload.name
        newComment.email = payload.email
        newComment.body = payload.body
        newComment.post = post

        return commentRepository.save(newComment).let { CommentDTO(it.id, it.name, it.email, it.body) }
    }

    override fun listAllComment(postId: Long): List<CommentDTO> {
        return commentRepository.findAllByPostIdOrderByIdDesc(postId).map { CommentDTO(it.id, it.name, it.email, it.body) }
    }

    override fun showCommentDetail(postId: Long, commentId: Long): CommentDTO? {
        return commentRepository.findByIdAndPostId(commentId, postId).let { CommentDTO(it?.id, it?.name, it?.email, it?.body) }
    }

    override fun updateComment(commentId: Long, postId: Long, payload: CommentDTO): CommentDTO? {
        val comment = commentRepository.findByIdAndPostId(commentId, postId) ?: return CommentDTO()

        comment.name = payload.name
        comment.email = payload.email
        comment.body = payload.body

        return commentRepository.save(comment).let { CommentDTO(it.id, it.name, it.email, it.body) }
    }

    override fun deleteComment(commentId: Long): Boolean? {
        return try {
            commentRepository.deleteById(commentId)
            true
        }catch (ex: RuntimeException){
            println(ex.message.toString())
            false
        }
    }

}