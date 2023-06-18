package com.kips.backend.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void uploadFile(MultipartFile file,Integer entityId);
    Resource loadFileAsResource(String fileName);
}
