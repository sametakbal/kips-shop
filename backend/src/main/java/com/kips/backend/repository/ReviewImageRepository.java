package com.kips.backend.repository;

import com.kips.backend.domain.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Integer>{
    Optional<ReviewImage> findByName(String fileName);

    void deleteAllByReviewId(Integer reviewId);
}
