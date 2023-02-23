package com.phoemphanith.blogserviceapibasic.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain{
        http.csrf().disable()
            .authorizeHttpRequests { authorize -> authorize.anyRequest().authenticated() }
            .httpBasic(Customizer.withDefaults())

        return http.build()
    }
}