package com.phoemphanith.blogserviceapibasic

import org.modelmapper.ModelMapper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class BlogServiceApiBasicApplication

@Bean
fun modelMapper(): ModelMapper{
	return ModelMapper()
}

fun main(args: Array<String>) {
	runApplication<BlogServiceApiBasicApplication>(*args)
}
