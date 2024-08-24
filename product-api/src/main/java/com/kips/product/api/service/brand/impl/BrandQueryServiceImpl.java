package com.kips.product.api.service.brand.impl;

import com.kips.product.api.common.constants.CacheNames;
import com.kips.product.api.dto.response.BrandResponse;
import com.kips.product.api.repository.BrandRepository;
import com.kips.product.api.service.brand.BrandQueryService;
import com.kips.product.api.service.brand.mapper.BrandMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandQueryServiceImpl implements BrandQueryService {

    private final BrandMapper brandMapper;
    private final BrandRepository brandRepository;

    @Override
    @Cacheable(CacheNames.BRANDS)
    public List<BrandResponse> getAllBrands() {
        var brandEntities = brandRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        return brandMapper.toResponseList(brandEntities);
    }
}
