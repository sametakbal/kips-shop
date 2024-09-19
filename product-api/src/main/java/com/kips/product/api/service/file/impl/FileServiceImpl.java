package com.kips.product.api.service.file.impl;

import com.kips.product.api.domain.ProductEntity;
import com.kips.product.api.domain.ProductImage;
import com.kips.product.api.domain.enumaretion.EntityType;
import com.kips.product.api.exception.FileDeleteException;
import com.kips.product.api.exception.FileDownloadErrorException;
import com.kips.product.api.exception.FileNotFoundException;
import com.kips.product.api.exception.FileUploadException;
import com.kips.product.api.repository.ProductImageRepository;
import com.kips.product.api.service.file.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    public static final String UPLOAD_DIR = "./uploads/";
    public static final String FILE_NOT_FOUND = "File not found";

    private final ProductImageRepository productImageRepository;

    @Override
    public void uploadFiles(Set<MultipartFile> files, Long entityId, EntityType entityType) {
        int order = 1;
        for (MultipartFile file : files) {
            uploadFile(file, entityId, entityType, order++);
        }
    }

    @Override
    public void uploadFile(MultipartFile file, Long entityId, EntityType entityType, Integer order) {

        if (file.isEmpty()) {
            throw new FileNotFoundException(FILE_NOT_FOUND);
        }

        String extension = getExtensionByStringHandling(file.getOriginalFilename());
        String uploadDir = UPLOAD_DIR + entityType.getType() + "/";

        try {
            createDirectoryIfNotExists(UPLOAD_DIR);
            createDirectoryIfNotExists(uploadDir);
            uploadDir += entityId + "/";

            createDirectoryIfNotExists(uploadDir);
            String fullFilename = String.format("%s.%s", UUID.randomUUID(), extension);
            String filePath = uploadDir + fullFilename;
            Path path = Paths.get(filePath);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            if (EntityType.PRODUCT.equals(entityType)) {
                ProductImage productImage = new ProductImage();
                productImage.setProduct(new ProductEntity(entityId));
                productImage.setName(fullFilename);
                productImage.setPath(filePath);
                productImage.setType(file.getContentType());
                productImage.setOrderNum(order);
                productImageRepository.save(productImage);
            }

        } catch (IOException e) {
            throw new FileUploadException(e.getMessage());
        }
    }

    private void createDirectoryIfNotExists(String dirPath) throws IOException {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            log.info("Creating directory: {}", dirPath);
            if (!dir.mkdirs()) {
                log.error("Directory creation failed: {}", dirPath);
                throw new IOException("Directory creation failed: " + dirPath);
            }
        }
    }

    @Override
    public Resource loadFileAsResource(EntityType type, String name) {
        try {
            String filePath = "";
            if (EntityType.PRODUCT.equals(type)) {
                filePath = productImageRepository.findByName(name)
                        .orElseThrow(() -> new FileNotFoundException(FILE_NOT_FOUND))
                        .getPath();
            }

            Path fileStorageLocation = getPath(filePath);
            Resource resource = new UrlResource(fileStorageLocation.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                log.error("File not found file name: {}", name);
                throw new FileNotFoundException("File not found entity name: " + name);
            }
        } catch (Exception ex) {
            throw new FileDownloadErrorException(ex.getMessage());
        }
    }

    @Override
    public void deleteFiles(Set<ProductImage> images) {
        images.forEach(image -> {
            deleteFileWithFileName(image.getName());
            productImageRepository.delete(image);
        });
    }

    private void deleteFileWithFileName(String fileName) {
        ProductImage fileEntity = productImageRepository.findByName(fileName)
                .orElseThrow(() -> new FileNotFoundException(FILE_NOT_FOUND));

        Path fileStorageLocation = getPath(fileEntity.getPath());

        try {
            Files.delete(fileStorageLocation);
            productImageRepository.delete(fileEntity);
        } catch (IOException e) {
            throw new FileDeleteException(e.getMessage());
        }

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
