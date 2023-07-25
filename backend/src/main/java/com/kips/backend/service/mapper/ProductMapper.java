package com.kips.backend.service.mapper;


import com.kips.backend.domain.Brand;
import com.kips.backend.domain.Category;
import com.kips.backend.domain.Product;
import com.kips.backend.service.dto.ProductDto;
import com.kips.backend.service.request.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductMapper extends Mapper{

    @Value("${file-url.product}")
    private String fileDownloadUrl;

    public ProductDto toDto(Product product) {
        if (product == null) {
            return null;
        }
        List<String> photos = getImageUrls(fileDownloadUrl,product.getImages());

        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .countInStock(product.getCountInStock())
                .numReviews(product.getNumReviews())
                .rating(product.getRating())
                .images(photos)
                .build();
    }

    public Product toEntity(ProductRequest productDto) {
        if (productDto == null) {
            return null;
        }
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .description(productDto.getDescription())
                .brand(Brand.builder().id(productDto.getBrandId()).build())
                .category(Category.builder().id(productDto.getCategoryId()).build())
                .countInStock(productDto.getCountInStock())
                .numReviews(productDto.getNumReviews())
                .rating(productDto.getRating())
                .build();
    }

    public Product productFromId(Integer id) {
        if (id == null) {
            return null;
        }
        return Product.builder().id(id).build();
    }

    public List<ProductDto> toDtoList(List<Product> products) {
        return products.stream().map(this::toDto).toList();
    }

    public Page<ProductDto> toDtoPage(Page<Product> productDtoPage){
        return productDtoPage.map(this::toDto);
    }
}
