package com.kips.product.api.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCreateRequest {
    @NotNull
    private String name;
    @NotNull
    @Size(min = 100, max = 2000)
    private String description;
    @NotNull
    @Positive
    private Integer stock;
    @NotNull
    @PositiveOrZero
    private Double price;
    @NotNull
    private Long categoryId;
    @NotNull
    private Long brandId;
    private Set<MultipartFile> photoFiles;
}
