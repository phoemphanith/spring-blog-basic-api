package com.phoemphanith.blogserviceapibasic.security

import com.phoemphanith.blogserviceapibasic.exception.CustomException
import com.phoemphanith.blogserviceapibasic.payload.enumerate.HttpCode
import com.phoemphanith.blogserviceapibasic.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class CustomUserDetailsService: UserDetailsService {
    @Autowired
    lateinit var userRepository: UserRepository
    override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findByEmail(email)
            ?: throw CustomException(HttpCode.NOT_FOUND, "User given by email is not found")

        val authorities = user.roles?.stream()?.map {
            SimpleGrantedAuthority(it.name)
        }?.collect(Collectors.toSet())

        return User(user.email, user.password, authorities)
    }
}