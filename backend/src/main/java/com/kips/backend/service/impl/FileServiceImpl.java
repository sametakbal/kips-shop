package com.kips.backend.service.impl;

import com.kips.backend.common.exception.GeneralException;
import com.kips.backend.domain.EntityType;
import com.kips.backend.domain.Product;
import com.kips.backend.domain.ProductImage;
import com.kips.backend.repository.ProductImageRepository;
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
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

    public static final String UPLOAD_DIR = "./uploads/";
    public static final String FILE_NOT_FOUND = "File not found";

    private final ProductImageRepository productImageRepository;

    @Override
    public void uploadFile(MultipartFile file, Integer entityId, EntityType entityType) {

        if (file.isEmpty()) {
            throw new GeneralException(FILE_NOT_FOUND);
        }
        String extension = getExtensionByStringHandling(file.getOriginalFilename());
        String uploadDir = UPLOAD_DIR + entityType.name().toLowerCase() + "/";

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
            ProductImage productImage = productImageRepository.findByName(fileName).orElseThrow(() -> new GeneralException(FILE_NOT_FOUND));
            Path fileStorageLocation = Paths.get(productImage.getPath())
                    .toAbsolutePath().normalize();
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

        Path fileStorageLocation = Paths.get(fileEntity.getPath())
                .toAbsolutePath().normalize();

        File file = new File(fileStorageLocation.toString());

        if (!file.exists()) {
            throw new GeneralException("File not deleted ");
        }

        file.delete();
        productImageRepository.delete(fileEntity);
    }

    private String getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1)).orElseThrow();
    }
}