package com.kips.backend.controller;

import com.kips.backend.service.CategoryService;
import com.kips.backend.service.ProductService;
import com.kips.backend.service.dto.CategoryDto;
import com.kips.backend.service.request.ProductRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
@Tag(name = "Admin Controller")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final ProductService productService;
    private final CategoryService categoryService;


    @PostMapping(name="/products/save",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> saveProduct(ProductRequest dto) {
        productService.save(dto);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping({"/products/delete/{id}"})
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/categories")
    public ResponseEntity<Void> create(@RequestBody CategoryDto categoryDto){
        categoryService.create(categoryDto);
        return ResponseEntity.ok().build();
    }

}
