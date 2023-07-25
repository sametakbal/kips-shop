package com.kips.backend.controller;

import com.kips.backend.service.ProductService;
import com.kips.backend.service.dto.ProductDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
@Tag(name = "Product Controller")
public class ProductController {

    private final ProductService service;


    @GetMapping
    public ResponseEntity<Page<ProductDto>> fetchProductPage(@RequestParam(defaultValue = "0") Integer page,
                                                             @RequestParam(defaultValue = "10") Integer pageSize,
                                                             @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok(service.getProductPage(PageRequest.of(page, pageSize, Sort.by(sortBy))));
    }


    @GetMapping({"/{id}"})
    public ResponseEntity<ProductDto> getProduct(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }



    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getProducts() {
        return ResponseEntity.ok(service.getProducts());
    }

}
