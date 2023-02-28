package com.phoemphanith.blogserviceapibasic.controller

import com.phoemphanith.blogserviceapibasic.payload.CategoryDTO
import com.phoemphanith.blogserviceapibasic.payload.response.ResponseObjectMap
import com.phoemphanith.blogserviceapibasic.service.CategoryService
import com.phoemphanith.blogserviceapibasic.utils.AppConstant
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(AppConstant.MAIN_ENDPOINT + "/categories")
class CategoryController {
    @Autowired
    lateinit var response: ResponseObjectMap
    @Autowired
    lateinit var categoryService: CategoryService

    @PostMapping
    fun createCategory(@RequestBody category: CategoryDTO): MutableMap<String, Any?>{
        return response.body(categoryService.createItem(category))
    }

    @GetMapping
    fun listAllCategory() = response.list(categoryService.listAll())

    @GetMapping("/list")
    fun listAllCategory(
        @RequestParam(name = "page", defaultValue = "0", required = false) page: Int,
        @RequestParam(name = "size", defaultValue = "10", required = false) size: Int
    ): MutableMap<String, Any>{
        return response.paginate(categoryService.paginationList(page, size), page, size)
    }

    @GetMapping("/{id}")
    fun getCategoryDetail(@PathVariable id: Long): MutableMap<String, Any?>{
        return response.body(categoryService.showDetail(id))
    }

    @PutMapping("/{id}")
    fun updateCategory(@PathVariable id: Long, @RequestBody category: CategoryDTO): MutableMap<String, Any?>{
        return response.body(categoryService.updateItem(id, category))
    }

    @DeleteMapping("/{id}")
    fun deleteCategory(@PathVariable id: Long): MutableMap<String, Any?>{
        return response.body(categoryService.deleteItem(id))
    }
}