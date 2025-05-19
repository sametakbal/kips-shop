package com.akbal.kips.be.domain.product;


import com.akbal.kips.be.domain.common.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "category")
@NoArgsConstructor
public class ProductCategory extends BaseEntity {

    public ProductCategory(Long id) {
        setId(id);
    }

    public ProductCategory(Long id, String name,
                           ProductCategory parent,
                           List<ProductCategory> children) {
        setId(id);
        this.name = name;
        this.parent = parent;
        this.children = children;
    }

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private ProductCategory parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductCategory> children = new ArrayList<>();

}
