package com.kips.product.api.controller.product;

import com.kips.product.api.controller.BaseController;
import com.kips.product.api.dto.request.AttributeRequest;
import com.kips.product.api.dto.request.ProductFilterRequest;
import com.kips.product.api.dto.response.DataResponse;
import com.kips.product.api.dto.response.ProductDetailResponse;
import com.kips.product.api.dto.response.ProductResponse;
import com.kips.product.api.service.product.ProductQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController extends BaseController {
    private final ProductQueryService queryService;

    @GetMapping
    public ResponseEntity<DataResponse<ProductResponse>> getAllProducts(ProductFilterRequest filterRequest, @RequestBody(required = false) AttributeRequest attributes) {
        var productsPage = queryService.getAllProducts(filterRequest, attributes);
        return respond(productsPage.getContent(), productsPage.getTotalElements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailResponse> getProductById(@PathVariable Long id) {
        var product = queryService.getProductById(id);
        return respond(product);
    }
}
