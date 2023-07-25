package com.kips.backend.service.mapper;

import com.kips.backend.domain.Review;
import com.kips.backend.service.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewMapper  extends Mapper{

    @Value("${file-url.review}")
    private String fileDownloadUrl;

    private final ProductMapper productMapper;
    private final UserMapper userMapper;

    public ReviewDto toDto(Review review) {
        if (review == null) {
            return null;
        }
        List<String> photos = getImageUrls(fileDownloadUrl,review.getImages());

        return ReviewDto.builder()
                .id(review.getId())
                .comment(review.getComment())
                .rating(review.getRating())
                .product(review.getProduct().getName())
                .user(review.getUser().getUsername())
                .images(photos)
                .build();
    }

    public Review toEntity(ReviewDto reviewDto) {
        if (reviewDto == null) {
            return null;
        }
        return Review.builder()
                .id(reviewDto.getId())
                .comment(reviewDto.getComment())
                .rating(reviewDto.getRating())
                .product(productMapper.productFromId(reviewDto.getProductId()))
                .user(userMapper.userFromId(reviewDto.getUserId()))
                .build();
    }

    public List<ReviewDto> toDtoList(List<Review> reviews) {
        return reviews.stream().map(this::toDto).toList();
    }
}
