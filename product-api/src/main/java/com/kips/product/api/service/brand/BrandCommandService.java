package com.kips.product.api.service.brand;

import com.kips.product.api.dto.request.BrandCreateOrUpdateRequest;

public interface BrandCommandService {
    void saveBrand(BrandCreateOrUpdateRequest request);

    void deleteBrand(Long id);
}
