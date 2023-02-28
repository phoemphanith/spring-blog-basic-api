package com.phoemphanith.blogserviceapibasic.repository

import com.phoemphanith.blogserviceapibasic.entity.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository: JpaRepository<Category, Long> {
}