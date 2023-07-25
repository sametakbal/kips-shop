package com.kips.backend.controller;

import com.kips.backend.common.exception.GeneralException;
import com.kips.backend.domain.EntityType;
import com.kips.backend.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "File Controller")
public class FileController {

    private final FileService fileService;


    @Operation(
            description = "Description for file download endpoint",
            summary = "This is a summary for management get endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            }

    )
    @GetMapping("/download/{entityType}/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String entityType,@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = fileService.loadFileAsResource(fileName, EntityType.valueOf(entityType.toUpperCase()));
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
