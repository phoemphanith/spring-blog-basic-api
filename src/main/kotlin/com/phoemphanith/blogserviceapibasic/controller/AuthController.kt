package com.phoemphanith.blogserviceapibasic.controller

import com.phoemphanith.blogserviceapibasic.payload.LoginDTO
import com.phoemphanith.blogserviceapibasic.payload.RegisterDTO
import com.phoemphanith.blogserviceapibasic.payload.response.ResponseObjectMap
import com.phoemphanith.blogserviceapibasic.service.AuthService
import com.phoemphanith.blogserviceapibasic.utils.AppConstant
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(AppConstant.MAIN_ENDPOINT + "/auth")
class AuthController {
    @Autowired
    lateinit var response: ResponseObjectMap
    @Autowired
    lateinit var authService: AuthService

    @PostMapping("/login")
    fun authenticate(@Valid @RequestBody login: LoginDTO): MutableMap<String, Any?>{
        return response.body(authService.login(login))
    }

    @PostMapping("/register")
    fun createUser(@Valid @RequestBody register: RegisterDTO): MutableMap<String, Any?>{
        return response.body(authService.register(register))
    }
}