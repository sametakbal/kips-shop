package com.kips.backend.service.impl;

import com.kips.backend.common.exception.GeneralException;
import com.kips.backend.common.util.ValidationUtil;
import com.kips.backend.domain.EntityType;
import com.kips.backend.domain.Review;
import com.kips.backend.repository.ReviewImageRepository;
import com.kips.backend.repository.ReviewRepository;
import com.kips.backend.service.FileService;
import com.kips.backend.service.ReviewService;
import com.kips.backend.service.dto.ReviewDto;
import com.kips.backend.service.mapper.ReviewMapper;
import com.kips.backend.service.request.ReviewRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final FileService fileService;

    private final ReviewMapper reviewMapper;

    @Override
    public ReviewDto getReview(Integer reviewId) {
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        if (reviewOptional.isPresent()) {
            Review review = reviewOptional.get();
            return reviewMapper.toDto(review);
        }
        throw new GeneralException("Review not found");
    }

    @Override
    public void createReview(ReviewRequest createRequest) {
        ValidationUtil.fieldCheckNullOrEmpty(createRequest.getComment(), "Comment");
        ValidationUtil.fieldCheckNullOrEmpty(createRequest.getRating(), "Rating");
        ValidationUtil.fieldCheckNullOrEmpty(createRequest.getProductId(), "Product");
        ValidationUtil.fieldCheckNullOrEmpty(createRequest.getUserId(), "User");

        Review review = reviewMapper.toEntity(createRequest);
        Review saved = reviewRepository.save(review);

        createRequest.getPhotoFiles().forEach(file -> fileService.uploadFile(file, saved.getId(), EntityType.PRODUCT));
    }

    @Override
    public void deleteReview(Integer reviewId) {
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);

        if (reviewOptional.isEmpty()) {
            throw new GeneralException("Review not found");
        }

        Review review = reviewOptional.get();
        fileService.deleteReviewImages(review.getImages());
        reviewImageRepository.deleteAllByReviewId(reviewId);
        reviewRepository.delete(review);
    }
}
