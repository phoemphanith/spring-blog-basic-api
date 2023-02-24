package com.phoemphanith.blogserviceapibasic.security

import com.phoemphanith.blogserviceapibasic.exception.CustomException
import com.phoemphanith.blogserviceapibasic.payload.enumerate.HttpCode
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.lang.IllegalArgumentException
import java.security.Key
import java.util.Date

@Component
class JwtTokenProvider {
    @Value("\${app.jwt-secret}")
    private var jwtSecretKey: String? = null
    @Value("\${app.jwt-expiration-milliseconds}")
    private var jwtExpiredDate: Long? = null

    // Generate Token From Authentication
    fun generateToken(authentication: Authentication): String? {
        val username = authentication.name

        val currentDate = Date()
        val expirationDate = Date(currentDate.time + jwtExpiredDate!!)

        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(currentDate)
            .setExpiration(expirationDate)
            .signWith(key())
            .compact()
    }

    // Get username from token
    fun getUsername(token: String): String{
        val claim: Claims = Jwts.parserBuilder()
            .setSigningKey(key())
            .build()
            .parseClaimsJws(token)
            .body

        return claim.subject
    }

    // Validate JWT Token
    fun validateToken(token: String): Boolean{
        return try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(token)
            true
        } catch (ex : MalformedJwtException){
            throw CustomException(HttpCode.BAD_REQUEST, "Invalid JWT token")
        } catch (ex : ExpiredJwtException){
            throw CustomException(HttpCode.BAD_REQUEST, "Expire JWT token")
        } catch (ex : UnsupportedJwtException){
            throw CustomException(HttpCode.BAD_REQUEST, "Unsupported JWT token")
        } catch (ex : IllegalArgumentException){
            throw CustomException(HttpCode.BAD_REQUEST, "JWT claims string is empty")
        }
    }

    private fun key(): Key {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecretKey))
    }
}