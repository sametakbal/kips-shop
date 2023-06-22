package com.kips.backend.service.impl;

import com.kips.backend.common.exception.GeneralException;
import com.kips.backend.common.util.ValidationUtil;
import com.kips.backend.domain.EntityType;
import com.kips.backend.domain.Product;
import com.kips.backend.repository.ProductRepository;
import com.kips.backend.service.FileService;
import com.kips.backend.service.ProductService;
import com.kips.backend.service.dto.ProductCreateDto;
import com.kips.backend.service.dto.ProductDto;
import com.kips.backend.service.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final FileService fileService;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public void save(ProductCreateDto dto) {

        ValidationUtil.fieldCheckNullOrEmpty(dto.getName(), "name");
        ValidationUtil.fieldCheckNullOrEmpty(dto.getDescription(), "description");
        ValidationUtil.fieldCheckNullOrEmpty(dto.getCountInStock(), "countInStock");
        ValidationUtil.fieldCheckNullOrEmpty(dto.getPrice(), "price");
        ValidationUtil.fieldCheckNullOrEmpty(dto.getBrandId(), "brandId");

        Product entity = productMapper.toEntity(dto);
        Product saved = productRepository.save(entity);
        dto.getPhotoFiles().forEach(file -> fileService.uploadFile(file, saved.getId(), EntityType.PRODUCT));
    }

    @Override
    public ProductDto getById(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            return productMapper.toDto(product);
        }
        throw new GeneralException("Product not found");
    }

    @Override
    public List<ProductDto> getProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.toDtoList(products);
    }
}
