package com.akbal.kips.be.controller;


import com.akbal.kips.be.dto.response.ApiResponse;
import com.akbal.kips.be.dto.response.CategoryResponse;
import com.akbal.kips.be.service.category.CategoryQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
