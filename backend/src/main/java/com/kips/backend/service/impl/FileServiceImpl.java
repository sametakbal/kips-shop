package com.kips.backend.service.impl;

import com.kips.backend.common.exception.GeneralException;
import com.kips.backend.domain.*;
import com.kips.backend.repository.ProductImageRepository;
import com.kips.backend.repository.ReviewImageRepository;
import com.kips.backend.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

    public static final String UPLOAD_DIR = "./uploads/";
    public static final String FILE_NOT_FOUND = "File not found";

    private final ProductImageRepository productImageRepository;

    private final ReviewImageRepository reviewImageRepository;

    @Override
    public void uploadFile(MultipartFile file, Integer entityId, EntityType entityType) {

        if (file.isEmpty()) {
            throw new GeneralException(FILE_NOT_FOUND);
        }
        String extension = getExtensionByStringHandling(file.getOriginalFilename());
        String uploadDir = UPLOAD_DIR + entityType.getType() + "/";

        try {
            if (new File(uploadDir).exists() || (new File(uploadDir).mkdir())) {
                uploadDir += entityId + "/";
                if (new File(uploadDir).exists() || (new File(uploadDir).mkdir())) {
                    String fullFilename = String.format("%s.%s", UUID.randomUUID(), extension);
                    String filePath = uploadDir + fullFilename;
                    Path path = Paths.get(filePath);
                    Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                    if (entityType == EntityType.PRODUCT) {
                        ProductImage productImage = ProductImage.builder()
                                .name(fullFilename)
                                .path(filePath)
                                .type(file.getContentType())
                                .createdAt(LocalDateTime.now())
                                .updatedAt(LocalDateTime.now())
                                .product(Product.builder().id(entityId).build())
                                .build();

                        productImageRepository.save(productImage);
                    } else if (entityType == EntityType.REVIEW) {
                        ReviewImage reviewImage = ReviewImage.builder()
                                .name(fullFilename)
                                .path(filePath)
                                .type(file.getContentType())
                                .createdAt(LocalDateTime.now())
                                .updatedAt(LocalDateTime.now())
                                .review(Review.builder().id(entityId).build())
                                .build();

                        reviewImageRepository.save(reviewImage);
                    }
                }

            }
        } catch (IOException e) {
            throw new GeneralException(e.getMessage());
        }
    }


    @Override
    public Resource loadFileAsResource(String fileName, EntityType entityType) {
        try {
            String filePath = "";
            if (entityType == EntityType.PRODUCT) {
                filePath = productImageRepository
                        .findByName(fileName)
                        .orElseThrow(() -> new GeneralException(FILE_NOT_FOUND))
                        .getPath();
            } else if (entityType == EntityType.REVIEW) {
                filePath = reviewImageRepository
                        .findByName(fileName)
                        .orElseThrow(() -> new GeneralException(FILE_NOT_FOUND))
                        .getPath();
            }

            Path fileStorageLocation = getPath(filePath);
            Resource resource = new UrlResource(fileStorageLocation.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new GeneralException("File not found " + fileName);
            }
        } catch (Exception ex) {
            throw new GeneralException(ex.getMessage());
        }
    }

    @Override
    public void deleteFileWithFileName(String fileName, EntityType entityType) {
        ProductImage fileEntity = productImageRepository.findByName(fileName)
                .orElseThrow(() -> new GeneralException(FILE_NOT_FOUND));

        Path fileStorageLocation = getPath(fileEntity.getPath());

        try {
            Files.delete(fileStorageLocation);
            productImageRepository.delete(fileEntity);
        } catch (IOException e) {
            throw new GeneralException(e.getMessage());
        }

    }

    @Override
    public void deleteProductImage(ProductImage productImage) {

        Path fileStorageLocation = getPath(productImage.getPath());
        try {
            Files.delete(fileStorageLocation);
            productImageRepository.delete(productImage);
        } catch (IOException e) {
            throw new GeneralException(e.getMessage());
        }
    }

    @Override
    public void deleteReviewImage(ReviewImage reviewImage) {
        Path fileStorageLocation = getPath(reviewImage.getPath());
        try {
            Files.delete(fileStorageLocation);
            reviewImageRepository.delete(reviewImage);
        } catch (IOException e) {
            throw new GeneralException(e.getMessage());
        }
    }

    @Override
    public void deleteReviewImages(List<ReviewImage> images) {
        images.forEach(this::deleteReviewImage);
    }

    private String getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1)).orElseThrow();
    }

    private Path getPath(String path) {
        return Paths.get(path)
                .toAbsolutePath().normalize();
    }
}
