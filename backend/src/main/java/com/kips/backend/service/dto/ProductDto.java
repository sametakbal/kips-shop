package com.kips.backend.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
    private Integer id;
    private String name;
    private String description;
    private Integer brandId;
    private Integer categoryId;
    private Float price;
    private Integer countInStock;
    private Float rating;
    private Integer numReviews;
    private List<String> photos;
}
