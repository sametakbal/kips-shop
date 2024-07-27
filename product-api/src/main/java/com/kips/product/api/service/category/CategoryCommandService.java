package com.kips.product.api.service.category;

import com.kips.product.api.dto.request.CategoryCreateRequest;

public interface CategoryCommandService {
    void createCategory(CategoryCreateRequest request);
    void deleteCategory(Long id);
}
