package com.kips.product.api.service.file;

import com.kips.product.api.domain.ProductImage;
import com.kips.product.api.domain.enumaretion.EntityType;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface FileService {
    void uploadFiles(Set<MultipartFile> files, Long entityId, EntityType entityType);
    void uploadFile(MultipartFile file, Long entityId, EntityType entityType, Integer order);
    Resource loadFileAsResource(EntityType entityType,String name);
    void deleteFiles(Set<ProductImage> images);
}
