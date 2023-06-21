package com.kips.backend.service;

import com.kips.backend.service.dto.ProductCreateDto;
import com.kips.backend.service.dto.ProductDto;

public interface ProductService {
    void save(ProductCreateDto dto);

    ProductDto getById(Integer id);
}
