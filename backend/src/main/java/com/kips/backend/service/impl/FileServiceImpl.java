package com.kips.backend.service.impl;

import com.kips.backend.common.exception.GeneralException;
import com.kips.backend.domain.FileEntity;
import com.kips.backend.repository.FileRepository;
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

    private final FileRepository repository;

    @Override
    public void uploadFile(MultipartFile file, Integer entityId) {

        if (file.isEmpty()) {
            throw new GeneralException("File not found");
        }
        String extension = getExtensionByStringHandling(file.getOriginalFilename());
        String uploadDir = UPLOAD_DIR + entityId + "/";
        String fullFilename = String.format("%s.%s", UUID.randomUUID(), extension);
        String filePath = uploadDir + fullFilename;
        try {
            if (new File(uploadDir).exists() || (new File(uploadDir).mkdir())) {
                Path path = Paths.get(filePath);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                FileEntity fileEntity = FileEntity.builder()
                        .name(fullFilename)
                        .path(filePath)
                        .type(file.getContentType())
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build();
                repository.save(fileEntity);
            }
        } catch (IOException e) {
            throw new GeneralException(e.getMessage());
        }
    }

    @Override
    public Resource loadFileAsResource(String fileName) {
        try {
            FileEntity fileEntity = repository.findByName(fileName).orElseThrow(() -> new GeneralException("File not found"));
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
