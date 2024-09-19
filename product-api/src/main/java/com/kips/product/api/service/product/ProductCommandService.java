package com.kips.product.api.service.product;

import com.kips.product.api.dto.request.ProductCreateRequest;
import com.kips.product.api.dto.request.ProductUpdateRequest;
import com.kips.product.api.dto.response.ProductDetailResponse;

public interface ProductCommandService {
    ProductDetailResponse createProduct(ProductCreateRequest request);

    ProductDetailResponse updateProduct(ProductUpdateRequest request);

    void deleteProduct(Long id);
}
