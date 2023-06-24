package com.kips.backend.service;

import com.kips.backend.service.request.ProductRequest;
import com.kips.backend.service.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    void save(ProductRequest dto);

    ProductDto getById(Integer id);

    List<ProductDto> getProducts();

    Page<ProductDto> getProductPage(Pageable pageable);

    void deleteById(Integer id);
}
