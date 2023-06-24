package com.kips.backend.service.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kips.backend.service.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductRequest extends ProductDto {
    private List<MultipartFile> photoFiles;
}
