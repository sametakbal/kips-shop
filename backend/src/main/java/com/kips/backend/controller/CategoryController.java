package com.kips.backend.controller;


import com.kips.backend.service.CategoryService;
import com.kips.backend.service.dto.CategoryDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
@Tag(name = "Category Controller")
public class CategoryController {

    private final CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> fetchCategories(){
        return ResponseEntity.ok(service.getCategories());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> fetchCategoryById(@PathVariable Integer id){
        CategoryDto dto = service.getById(id);
        return ResponseEntity.ok(dto);
    }

}
