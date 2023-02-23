package com.phoemphanith.blogserviceapibasic.repository

import com.phoemphanith.blogserviceapibasic.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
    fun findByUsername(username: String): User?
    fun findByEmailOrUsername(email: String, username: String): User?
    fun existsByUsername(username: String): Boolean
    fun existsByEmail(email: String): Boolean
}