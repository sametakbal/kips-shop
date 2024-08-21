package com.kips.product.api.service.brand;

import com.kips.product.api.dto.response.BrandResponse;

import java.util.List;

public interface BrandQueryService {
    List<BrandResponse> getAllBrands();
}
