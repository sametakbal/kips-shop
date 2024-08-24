package com.kips.product.api.service.brand.impl;

import com.kips.product.api.dto.request.BrandCreateOrUpdateRequest;
import com.kips.product.api.repository.BrandRepository;
import com.kips.product.api.service.brand.BrandCommandService;
import com.kips.product.api.service.brand.mapper.BrandMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandCommandServiceImpl implements BrandCommandService {
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Override
    public void saveBrand(BrandCreateOrUpdateRequest request) {
        if (request.getId() == null) {
            var brandEntity = brandMapper.toEntity(request);
            brandRepository.save(brandEntity);
        } else {
            var brandEntity = brandRepository.findById(request.getId()).orElseThrow();
            brandEntity.setName(request.getName());
            brandRepository.save(brandEntity);
        }
    }

    @Override
    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }
}
