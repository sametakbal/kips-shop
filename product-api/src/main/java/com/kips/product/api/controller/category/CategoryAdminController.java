package com.kips.product.api.controller.category;


import com.kips.product.api.dto.request.CategoryCreateRequest;
import com.kips.product.api.service.category.CategoryCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/categories")
public class CategoryAdminController {

    private final CategoryCommandService commandService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createCategory(@RequestBody @Valid CategoryCreateRequest request) {
        commandService.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        commandService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
