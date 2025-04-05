package com.akbal.kips.be.controller;


import com.akbal.kips.be.dto.request.CategoryCreateRequest;
import com.akbal.kips.be.dto.response.ApiResponse;
import com.akbal.kips.be.dto.response.CategoryResponse;
import com.akbal.kips.be.service.category.CategoryQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController extends BaseController {

    private final CategoryQueryService service;


    @GetMapping("/all")
    public ApiResponse<List<CategoryResponse>> getAllCategories() {
        var categories = service.getAllCategories();
        return respond(categories);
    }

    @PostMapping
    public ResponseEntity<Void> createCategory(@RequestBody @Valid CategoryCreateRequest request) {
        log.info("Create category: {}", request);
        return ResponseEntity.ok().build();
    }

}
