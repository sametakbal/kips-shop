package com.kips.backend.controller;

import com.kips.backend.service.ProductService;
import com.kips.backend.service.request.ProductRequest;
import com.kips.backend.service.dto.ProductDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping
    public ResponseEntity<Page<ProductDto>> fetchProductPage(@RequestParam(defaultValue = "0") Integer page,
                                                             @RequestParam(defaultValue = "10") Integer pageSize,
                                                             @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok(service.getProductPage(PageRequest.of(page, pageSize, Sort.by(sortBy))));
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> saveProduct(ProductRequest dto) {
        service.save(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<ProductDto> getProduct(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getProducts() {
        return ResponseEntity.ok(service.getProducts());
    }

}
