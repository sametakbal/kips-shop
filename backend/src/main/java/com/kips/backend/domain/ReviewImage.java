package com.kips.backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "review_images")
public class ReviewImage extends FileEntity{

    @Builder
    public ReviewImage(Integer id, String name, String path, String type,
                        LocalDateTime createdAt, LocalDateTime updatedAt, Review review) {
        super(id, name, path, type, createdAt, updatedAt);
        this.review = review;
    }

    @ManyToOne
    @JoinColumn(name = "review_id",nullable = false)
    private Review review;
}
