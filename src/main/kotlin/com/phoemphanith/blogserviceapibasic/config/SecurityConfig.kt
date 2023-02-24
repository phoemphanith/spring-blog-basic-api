package com.phoemphanith.blogserviceapibasic.config

import com.phoemphanith.blogserviceapibasic.security.JwtAuthenticationEntryPoint
import com.phoemphanith.blogserviceapibasic.security.JwtAuthenticationFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableMethodSecurity
class SecurityConfig {

    @Autowired
    lateinit var authenticationEntryPoint: JwtAuthenticationEntryPoint
    @Autowired
    lateinit var authenticationFilter: JwtAuthenticationFilter

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
            .authorizeHttpRequests { authorize -> authorize
                .requestMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
                .requestMatchers("/api/v1/auth/**").permitAll()
                .anyRequest().authenticated()
            }
            .exceptionHandling{
                it.authenticationEntryPoint(authenticationEntryPoint)
            }
            .sessionManagement{
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }

        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

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