package com.kips.backend.service.impl;

import com.kips.backend.common.exception.GeneralException;
import com.kips.backend.domain.EntityType;
import com.kips.backend.domain.FileEntity;
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

    private final ProductImageRepository productImageRepository;

    @Override
    public void uploadFile(MultipartFile file, Integer entityId, EntityType entityType) {

        if (file.isEmpty()) {
            throw new GeneralException("File not found");
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
    public Resource loadFileAsResource(String fileName) {
        try {
            FileEntity fileEntity = productImageRepository.findByName(fileName).orElseThrow(() -> new GeneralException("File not found"));
            Path fileStorageLocation = Paths.get(fileEntity.getPath())
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

    private String getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1)).orElseThrow();
    }
}
