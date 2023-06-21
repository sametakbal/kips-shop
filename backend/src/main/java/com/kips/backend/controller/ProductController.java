package com.kips.backend.controller;

import com.kips.backend.service.ProductService;
import com.kips.backend.service.dto.ProductCreateDto;
import com.kips.backend.service.dto.ProductDto;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> createProduct(ProductCreateDto dto) {
        productService.save(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<ProductDto> getProduct(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.getById(id));
    }
}
