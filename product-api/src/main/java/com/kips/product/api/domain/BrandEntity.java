package com.kips.product.api.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "brand")
@NoArgsConstructor
@AllArgsConstructor
public class BrandEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;
}
