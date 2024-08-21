package com.kips.product.api.controller.category;

import com.kips.product.api.controller.BaseController;
import com.kips.product.api.dto.response.CategoryResponse;
import com.kips.product.api.dto.response.DataResponse;
import com.kips.product.api.service.category.CategoryQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController extends BaseController {

    private final CategoryQueryService service;

    @GetMapping
    public ResponseEntity<DataResponse<CategoryResponse>> getCategories() {
        var categories = service.getCategories();
        return respond(categories);
    }

    @GetMapping("/all")
    public ResponseEntity<DataResponse<CategoryResponse>> getAllCategories() {
        var categories = service.getAllCategories();
        return respond(categories);
    }

    @GetMapping("/{parentId}")
    public ResponseEntity<DataResponse<CategoryResponse>> getCategoriesByParent(@PathVariable Long parentId) {
        var categories = service.getCategoriesByParent(parentId);
        return respond(categories);
    }


}
