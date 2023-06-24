package com.kips.backend.repository;

import com.kips.backend.domain.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer>{
    Optional<ProductImage> findByName(String name);

    List<ProductImage> findAllByProductId(Integer productId);
}
