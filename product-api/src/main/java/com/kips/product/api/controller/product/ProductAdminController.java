package com.kips.product.api.controller.product;

import com.kips.product.api.controller.BaseController;
import com.kips.product.api.dto.request.ProductCreateRequest;
import com.kips.product.api.dto.request.ProductUpdateRequest;
import com.kips.product.api.dto.response.ProductDetailResponse;
import com.kips.product.api.service.product.ProductCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/products")
public class ProductAdminController extends BaseController {

    private final ProductCommandService service;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductDetailResponse> saveProduct(ProductCreateRequest request) {
        var response = service.createProduct(request);
        return respond(response);
    }

    @PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductDetailResponse> updateProduct(ProductUpdateRequest request) {
        var response = service.updateProduct(request);
        return respond(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
