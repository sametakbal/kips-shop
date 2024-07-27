package com.kips.product.api.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity extends BaseEntity {

    public CategoryEntity(Long id) {
        setId(id);
    }

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private CategoryEntity parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryEntity> children = new ArrayList<>();

}
