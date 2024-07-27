package com.kips.product.api.repository;

import com.kips.product.api.domain.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    List<CategoryEntity> findAllByParentIsNull();
}
