package com.kips.backend.service;

import com.kips.backend.domain.EntityType;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void uploadFile(MultipartFile file, Integer entityId, EntityType entityType);
    Resource loadFileAsResource(String fileName, EntityType entityType);
    void deleteFileWithFileName(String fileName, EntityType entityType);
}
