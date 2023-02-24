package com.phoemphanith.blogserviceapibasic.payload.response

class JwtAuthResponse (
    var accessToken: String? = null,
    var tokenType: String = "Bearer"
)