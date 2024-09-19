package com.kips.product.api.controller.file;

import com.kips.product.api.domain.enumaretion.EntityType;
import com.kips.product.api.exception.FileDownloadErrorException;
import com.kips.product.api.service.file.FileService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping("/download/{entityType}/{name}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String name,
                                                 @PathVariable String entityType,
                                                 HttpServletRequest request) {
        Resource resource = fileService.loadFileAsResource(EntityType.fromType(entityType), name);
        return getResourceResponse(request, resource);
    }

    ResponseEntity<Resource> getResourceResponse(HttpServletRequest request, Resource resource) {
        String contentType;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            throw new FileDownloadErrorException("Could not determine file type.");
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
