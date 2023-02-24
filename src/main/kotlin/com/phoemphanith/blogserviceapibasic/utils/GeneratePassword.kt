package com.phoemphanith.blogserviceapibasic.utils

import io.jsonwebtoken.io.Encoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.Base64.Encoder

class GeneratePassword

fun main(args: Array<String>){
    val passwordEncoder = BCryptPasswordEncoder()
    println(passwordEncoder.encode("admin"))
    println(passwordEncoder.encode("user"))
}
