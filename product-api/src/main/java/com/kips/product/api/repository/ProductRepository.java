package com.kips.product.api.repository;

import com.kips.product.api.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> , JpaSpecificationExecutor<ProductEntity> {
}
