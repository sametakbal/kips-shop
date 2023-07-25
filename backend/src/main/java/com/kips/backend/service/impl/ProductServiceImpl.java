package com.kips.backend.service.impl;

import com.kips.backend.common.exception.GeneralException;
import com.kips.backend.common.util.ValidationUtil;
import com.kips.backend.domain.EntityType;
import com.kips.backend.domain.Product;
import com.kips.backend.repository.ProductImageRepository;
import com.kips.backend.repository.ProductRepository;
import com.kips.backend.service.FileService;
import com.kips.backend.service.ProductService;
import com.kips.backend.service.mapper.ReviewMapper;
import com.kips.backend.service.request.ProductRequest;
import com.kips.backend.service.dto.ProductDto;
import com.kips.backend.service.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final FileService fileService;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ReviewMapper reviewMapper;
    private final ProductImageRepository productImageRepository;

    @Override
    public void save(ProductRequest dto) {

        ValidationUtil.fieldCheckNullOrEmpty(dto.getName(), "name");
        ValidationUtil.fieldCheckNullOrEmpty(dto.getDescription(), "description");
        ValidationUtil.fieldCheckNullOrEmpty(dto.getCountInStock(), "countInStock");
        ValidationUtil.fieldCheckNullOrEmpty(dto.getPrice(), "price");
        ValidationUtil.fieldCheckNullOrEmpty(dto.getBrandId(), "brandId");

        Product entity = productMapper.toEntity(dto);
        if (entity.getId() != null) {
            productImageRepository.findAllByProductId(entity.getId())
                    .forEach(fileService::deleteProductImage);
            entity.setImages(Collections.emptyList());
        }
        Product saved = productRepository.save(entity);
        dto.getPhotoFiles().forEach(file -> fileService.uploadFile(file, saved.getId(), EntityType.PRODUCT));
    }

    @Override
    public ProductDto getById(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            ProductDto dto = productMapper.toDto(product);
            dto.setReviews(reviewMapper.toDtoList(product.getReviews()));
            return dto;
        }
        throw new GeneralException("Product not found");
    }

    @Override
    public List<ProductDto> getProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.toDtoList(products);
    }

    @Override
    public Page<ProductDto> getProductPage(Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(pageable);
        return productMapper.toDtoPage(productPage);
    }

    @Override
    public void deleteById(Integer id) {
        productRepository.findById(id).ifPresent(product -> {
            productRepository.delete(product);
            product.getImages().forEach(fileService::deleteProductImage);
        });
    }
}
