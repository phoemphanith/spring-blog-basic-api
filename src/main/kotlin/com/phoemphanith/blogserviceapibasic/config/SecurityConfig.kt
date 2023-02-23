package com.phoemphanith.blogserviceapibasic.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableMethodSecurity
class SecurityConfig {

    @Bean
    fun passwordEncoder(): PasswordEncoder{
        return BCryptPasswordEncoder();
    }

    @Bean
    fun authenticationManager(configuration: AuthenticationConfiguration): AuthenticationManager{
        return configuration.authenticationManager
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain{
        http.csrf().disable()
            .authorizeHttpRequests {
                    authorize -> authorize.requestMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
                        .anyRequest().authenticated()
            }
            .httpBasic(Customizer.withDefaults())

        return http.build()
    }

//    @Bean
//    fun userDetailsService(): UserDetailsService{
//        val user = User.builder()
//            .username("user")
//            .password(passwordEncoder().encode("user"))
//            .roles("USER")
//            .build()
//
//        val admin = User.builder()
//            .username("admin")
//            .password(passwordEncoder().encode("admin"))
//            .roles("ADMIN")
//            .build()
//
//        return InMemoryUserDetailsManager(user, admin)
//    }
}