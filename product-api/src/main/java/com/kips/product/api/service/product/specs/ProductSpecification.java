package com.kips.product.api.service.product.specs;

import com.kips.product.api.domain.ProductAttributeEntity;
import com.kips.product.api.domain.BrandEntity;
import com.kips.product.api.domain.CategoryEntity;
import com.kips.product.api.domain.ProductEntity;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import java.math.BigDecimal;

public class ProductSpecification {

    private ProductSpecification() {}

    public static Specification<ProductEntity> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<ProductEntity> hasInStock() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.isTrue(root.get("inStock"));
    }

    public static Specification<ProductEntity> hasMinPrice(BigDecimal minPrice) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("price"), minPrice);
    }

    public static Specification<ProductEntity> hasMaxPrice(BigDecimal maxPrice) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThan(root.get("price"), maxPrice);
    }

    public static Specification<ProductEntity> isActive() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.isTrue(root.get("active"));
    }

    public static Specification<ProductEntity> isNotDeleted() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.isFalse(root.get("deleted"));
    }

    public static Specification<ProductEntity> hasBrand(Long brandId) {
        return (root, query, criteriaBuilder) -> {
            Join<ProductEntity, BrandEntity> brand = root.join("brand", JoinType.INNER);
            return criteriaBuilder.equal(brand.get("id"), brandId);
        };
    }

    public static Specification<ProductEntity> hasCategory(Long categoryId) {
        return (root, query, criteriaBuilder) -> {
            Join<ProductEntity, CategoryEntity> category = root.join("category", JoinType.INNER);
            return criteriaBuilder.equal(category.get("id"), categoryId);
        };
    }

    public static Specification<ProductEntity> hasAttribute(String attributeName, String attributeValue) {
        return (root, query, criteriaBuilder) -> {
            Join<ProductEntity, ProductAttributeEntity> attribute = root.join("attributes", JoinType.INNER);
            return criteriaBuilder.and(
                    criteriaBuilder.equal(attribute.get("name"), attributeName),
                    criteriaBuilder.equal(attribute.get("value"), attributeValue)
            );
        };
    }
}
