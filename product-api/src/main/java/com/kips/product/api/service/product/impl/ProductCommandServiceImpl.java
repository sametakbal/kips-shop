package com.kips.product.api.service.product.impl;

import com.kips.product.api.domain.ProductEntity;
import com.kips.product.api.domain.enumaretion.EntityType;
import com.kips.product.api.dto.request.ProductCreateRequest;
import com.kips.product.api.dto.request.ProductUpdateRequest;
import com.kips.product.api.dto.response.ProductDetailResponse;
import com.kips.product.api.repository.ProductRepository;
import com.kips.product.api.service.file.FileService;
import com.kips.product.api.service.product.ProductCommandService;
import com.kips.product.api.service.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductCommandServiceImpl implements ProductCommandService {

    private final ProductMapper mapper;
    private final ProductRepository repository;
    private final FileService fileService;

    @Override
    @Transactional
    public ProductDetailResponse createProduct(ProductCreateRequest request) {
        ProductEntity product = mapper.toEntity(request);
        repository.save(product);
        if (request.getPhotoFiles() != null && !request.getPhotoFiles().isEmpty()) {
            fileService.uploadFiles(request.getPhotoFiles(), product.getId(), EntityType.PRODUCT);
        }
        return mapper.toDetailResponse(product);
    }

    @Override
    public ProductDetailResponse updateProduct(ProductUpdateRequest request) {
        ProductEntity entity = repository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        ProductEntity product = mapper.updateToEntity(entity, request);
        if (request.getPhotoFiles() != null && !request.getPhotoFiles().isEmpty()) {
            fileService.deleteFiles(product.getImages());
            fileService.uploadFiles(request.getPhotoFiles(), product.getId(), EntityType.PRODUCT);
        }
        repository.save(product);
        return mapper.toDetailResponse(product);
    }

    @Override
    public void deleteProduct(Long id) {
        repository.findById(id).ifPresent(product -> {
            product.setDeleted(true);
            repository.save(product);
        });
    }
}
