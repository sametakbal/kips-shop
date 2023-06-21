package com.kips.backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "product_images")
public class ProductImage extends FileEntity{

    private static final long serialVersionUID = 1905122041950251207L;
    @Builder
    public ProductImage(Integer id, String name, String path, String type,
                        LocalDateTime createdAt, LocalDateTime updatedAt, Product product) {
        super(id, name, path, type, createdAt, updatedAt);
        this.product = product;
    }

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;
}
