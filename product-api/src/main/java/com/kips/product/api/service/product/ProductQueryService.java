package com.kips.product.api.service.product;

import com.kips.product.api.dto.request.AttributeRequest;
import com.kips.product.api.dto.request.ProductFilterRequest;
import com.kips.product.api.dto.response.ProductDetailResponse;
import com.kips.product.api.dto.response.ProductResponse;
import org.springframework.data.domain.Page;


public interface ProductQueryService {
    Page<ProductResponse> getAllProducts(ProductFilterRequest filterRequest, AttributeRequest attributeRequest);
    ProductDetailResponse getProductById(Long productId);
}
