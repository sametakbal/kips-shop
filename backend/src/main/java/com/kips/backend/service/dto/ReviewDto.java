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
public class ReviewDto {
    private Integer id;
    private String comment;
    private Float rating;
    private String product;
    private Integer productId;
    private String user;
    private Integer userId;
    private List<String> images;
}
