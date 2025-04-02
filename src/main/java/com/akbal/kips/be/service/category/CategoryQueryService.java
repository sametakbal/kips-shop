package com.akbal.kips.be.service.category;

import com.akbal.kips.be.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryQueryService {
    List<CategoryResponse> getAllCategories();
}
