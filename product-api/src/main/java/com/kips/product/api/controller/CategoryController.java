package com.kips.product.api.controller;

import com.kips.product.api.dto.response.CategoryResponse;
import com.kips.product.api.service.category.CategoryQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryQueryService service;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getCategories() {
        var categories = service.getCategories();
        return ResponseEntity.ok(categories);
    }

}
