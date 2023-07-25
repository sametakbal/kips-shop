package com.kips.backend.service;

import com.kips.backend.domain.EntityType;
import com.kips.backend.domain.ProductImage;
import com.kips.backend.domain.ReviewImage;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    void uploadFile(MultipartFile file, Integer entityId, EntityType entityType);
    Resource loadFileAsResource(String fileName, EntityType entityType);
    void deleteFileWithFileName(String fileName, EntityType entityType);
    void deleteProductImage(ProductImage productImage);
    void deleteReviewImage(ReviewImage reviewImage);

    void deleteReviewImages(List<ReviewImage> images);
}
