package com.kips.product.api.repository;

import com.kips.product.api.domain.ProductAttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductAttributeRepository extends JpaRepository<ProductAttributeEntity, Long> {
}
