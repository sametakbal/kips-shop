package com.kips.product.api.controller.brand;

import com.kips.product.api.dto.request.BrandCreateOrUpdateRequest;
import com.kips.product.api.service.brand.BrandCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/brands")
public class BrandAdminController {

    private final BrandCommandService commandService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBrand(@RequestBody @Valid BrandCreateOrUpdateRequest request) {
        commandService.saveBrand(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBrand(@PathVariable Long id) {
        commandService.deleteBrand(id);
    }

}
