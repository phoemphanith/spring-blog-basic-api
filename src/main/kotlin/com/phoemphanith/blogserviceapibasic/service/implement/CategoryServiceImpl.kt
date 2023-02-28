package com.phoemphanith.blogserviceapibasic.service.implement

import com.phoemphanith.blogserviceapibasic.entity.Category
import com.phoemphanith.blogserviceapibasic.exception.CustomException
import com.phoemphanith.blogserviceapibasic.payload.CategoryDTO
import com.phoemphanith.blogserviceapibasic.payload.enumerate.HttpCode
import com.phoemphanith.blogserviceapibasic.repository.CategoryRepository
import com.phoemphanith.blogserviceapibasic.service.CategoryService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class CategoryServiceImpl: CategoryService {
    @Autowired
    lateinit var categoryRepository: CategoryRepository
    private val mapper = ModelMapper()
    override fun createItem(payload: CategoryDTO): CategoryDTO? {
        val newCategory = categoryRepository.save(Category(null,payload.name, payload.description))
        return mapper.map(newCategory, CategoryDTO::class.java)
    }

    override fun listAll(): List<CategoryDTO> {
        return categoryRepository.findAll(Sort.by("id").descending()).map { mapper.map(it, CategoryDTO::class.java) }
    }

    override fun paginationList(page: Int, size: Int): Page<CategoryDTO> {
        val page: Pageable = PageRequest.of(page,size, Sort.by("id").descending())
        return categoryRepository.findAll(page).map { mapper.map(it, CategoryDTO::class.java) }
    }

    override fun showDetail(id: Long): CategoryDTO? {
        val category = categoryRepository.findById(id).orElse(null)
            ?: throw CustomException(HttpCode.NOT_FOUND, "Category given by id $id not found")
        return mapper.map(category, CategoryDTO::class.java)
    }

    override fun updateItem(id: Long, payload: CategoryDTO): CategoryDTO? {
        val category = categoryRepository.findById(id).orElse(null)
            ?: throw CustomException(HttpCode.NOT_FOUND, "Category given by id $id not found")

        category.name = payload.name
        category.description = payload.description
        val newCategory = categoryRepository.save(category)

        return mapper.map(newCategory, CategoryDTO::class.java)
    }

    override fun deleteItem(id: Long): Any? {
        return try {
            val category = categoryRepository.findById(id).orElse(null)
                ?: throw CustomException(HttpCode.NOT_FOUND, "Category given by id $id not found")

            categoryRepository.delete(category)
            true
        }catch (ex: RuntimeException){
            CustomException(HttpCode.BAD_REQUEST, ex.message.toString())
        }
    }
}

