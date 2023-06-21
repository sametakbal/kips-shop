package com.kips.backend.controller;

import com.kips.backend.common.exception.GeneralException;
import com.kips.backend.domain.EntityType;
import com.kips.backend.service.FileService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/file")
@AllArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping("/download/product/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = fileService.loadFileAsResource(fileName, EntityType.PRODUCT);
        return getResourceResponse(request, resource);
    }

    @DeleteMapping("/delete/product/{fileName}")
    public ResponseEntity<Void> deleteFile(@PathVariable String fileName) {
        fileService.deleteFileWithFileName(fileName, EntityType.PRODUCT);
        return ResponseEntity.noContent().build();
    }

    ResponseEntity<Resource> getResourceResponse(HttpServletRequest request, Resource resource) {
        String contentType;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            throw new GeneralException("Could not determine file type.");
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
