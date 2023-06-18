package com.kips.backend.controller;


import com.kips.backend.service.CategoryService;
import com.kips.backend.service.dto.CategoryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CategoryDto categoryDto){
        service.create(categoryDto);
        return ResponseEntity.ok().build();
    }

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
