package com.kips.backend.repository;

import com.kips.backend.domain.FileEntity;
import com.kips.backend.domain.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer>{
    Optional<ProductImage> findByName(String name);
}
