package com.phoemphanith.blogserviceapibasic.service.implement

import com.phoemphanith.blogserviceapibasic.entity.Role
import com.phoemphanith.blogserviceapibasic.entity.User
import com.phoemphanith.blogserviceapibasic.exception.CustomException
import com.phoemphanith.blogserviceapibasic.payload.LoginDTO
import com.phoemphanith.blogserviceapibasic.payload.RegisterDTO
import com.phoemphanith.blogserviceapibasic.payload.enumerate.HttpCode
import com.phoemphanith.blogserviceapibasic.repository.RoleRepository
import com.phoemphanith.blogserviceapibasic.repository.UserRepository
import com.phoemphanith.blogserviceapibasic.security.JwtTokenProvider
import com.phoemphanith.blogserviceapibasic.service.AuthService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl: AuthService {
    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var authenticationManager: AuthenticationManager
    @Autowired
    lateinit var roleRepository: RoleRepository
    @Autowired
    lateinit var jwtTokenProvider: JwtTokenProvider
    val passwordEncoder = BCryptPasswordEncoder()

    override fun login(user: LoginDTO): String? {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(user.email, user.password)
        )
        SecurityContextHolder.getContext().authentication = authentication

        return jwtTokenProvider.generateToken(authentication)
    }

    override fun register(user: RegisterDTO): String {
        if (userRepository.existsByEmail(user.email!!)){
            throw CustomException(HttpCode.BAD_REQUEST, "User given by email (${user.email}) existed")
        }

        val newUser = User()
        newUser.email = user.email
        newUser.name = user.name
        newUser.username = user.username
        newUser.password = passwordEncoder.encode(user.password)

        val roles: MutableSet<Role> = HashSet()
        val role: Role = roleRepository.findByName("ROLE_USER")
            ?: throw CustomException(HttpCode.NOT_FOUND, "Role given by name (ROLE_USER) not found")

        roles.add(role)
        newUser.roles = roles
        userRepository.save(newUser)

        return "Register user successfully"
    }
}