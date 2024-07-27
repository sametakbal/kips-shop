package com.kips.product.api.service.category;

import com.kips.product.api.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryQueryService {
    List<CategoryResponse> getCategories();
}
