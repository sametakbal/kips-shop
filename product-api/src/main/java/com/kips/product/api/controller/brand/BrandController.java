package com.kips.product.api.controller.brand;

import com.kips.product.api.controller.BaseController;
import com.kips.product.api.dto.response.BrandResponse;
import com.kips.product.api.dto.response.DataResponse;
import com.kips.product.api.service.brand.BrandQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/brands")
public class BrandController extends BaseController {
    private final BrandQueryService queryService;

    @GetMapping
    public ResponseEntity<DataResponse<BrandResponse>> getAllBrands() {
        var brands = queryService.getAllBrands();
        return respond(brands);
    }
}
