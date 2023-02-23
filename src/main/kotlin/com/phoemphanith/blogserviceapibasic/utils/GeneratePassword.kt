package com.phoemphanith.blogserviceapibasic.utils

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class GeneratePassword

fun main(args: Array<String>){
    val passwordEncoder = BCryptPasswordEncoder()
    println(passwordEncoder.encode("admin"))
    println(passwordEncoder.encode("user"))
}
