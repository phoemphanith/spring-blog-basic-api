package com.phoemphanith.blogserviceapibasic.service

import com.phoemphanith.blogserviceapibasic.payload.LoginDTO
import com.phoemphanith.blogserviceapibasic.payload.RegisterDTO

interface AuthService {
    fun login(user: LoginDTO): String?
    fun register(user: RegisterDTO): String
}