package com.phoemphanith.blogserviceapibasic.service

import com.phoemphanith.blogserviceapibasic.payload.CategoryDTO
import org.springframework.data.domain.Page

interface CategoryService {
    fun createItem(payload: CategoryDTO): CategoryDTO?
    fun listAll(): List<CategoryDTO>
    fun paginationList(page: Int, size: Int): Page<CategoryDTO>
    fun showDetail(id: Long): CategoryDTO?
    fun updateItem(id: Long, payload: CategoryDTO): CategoryDTO?
    fun deleteItem(id: Long): Any?
}