package com.kips.product.api.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class FileEntity extends BaseEntity {

    @Column(unique = true)
    private String name;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Boolean isDefault;

}
