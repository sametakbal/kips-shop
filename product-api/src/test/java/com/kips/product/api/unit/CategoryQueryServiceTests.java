package com.kips.product.api.unit;

import com.kips.product.api.domain.CategoryEntity;
import com.kips.product.api.dto.response.CategoryResponse;
import com.kips.product.api.repository.CategoryRepository;
import com.kips.product.api.service.category.impl.CategoryQueryServiceImpl;
import com.kips.product.api.service.category.mapper.CategoryMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryQueryServiceTests {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryQueryServiceImpl categoryQueryService;


    @Test
    void getAllCategories_whenCategoriesExist_thenReturnCategories() {
        var categories = List.of(new CategoryEntity(1L, "Electronics", null, List.of()));
        var expectedResponse = List.of(new CategoryResponse(1L, "Electronics", null));
        when(categoryRepository.findAllByParentIsNull()).thenReturn(categories);
        when(categoryMapper.toResponse(categories, true)).thenReturn(expectedResponse);

        var actualResponse = categoryQueryService.getAllCategories();

        assertEquals(expectedResponse, actualResponse);
        verify(categoryRepository, times(1)).findAllByParentIsNull();
        verify(categoryMapper, times(1)).toResponse(categories, true);
    }

    @Test
    void getAllCategories_whenNoCategoriesExist_thenReturnEmptyList() {
        when(categoryRepository.findAllByParentIsNull()).thenReturn(List.of());

        var actualResponse = categoryQueryService.getAllCategories();

        assertTrue(actualResponse.isEmpty());
        verify(categoryRepository, times(1)).findAllByParentIsNull();
        verify(categoryMapper, times(0)).toResponse(anyList(), anyBoolean());
    }

    @Test
    void getCategoriesByParent_whenCategoriesExist_thenReturnCategories() {
        var parentId = 1L;
        var categories = List.of(new CategoryEntity(2L, "Laptops", null, List.of()));
        var expectedResponse = List.of(new CategoryResponse(2L, "Laptops", null));
        when(categoryRepository.findAllByParentId(parentId)).thenReturn(categories);
        when(categoryMapper.toResponse(categories, true)).thenReturn(expectedResponse);

        var actualResponse = categoryQueryService.getCategoriesByParent(parentId);

        assertEquals(expectedResponse, actualResponse);
        verify(categoryRepository, times(1)).findAllByParentId(parentId);
        verify(categoryMapper, times(1)).toResponse(categories, true);
    }

    @Test
    void getCategoriesByParent_whenNoCategoriesExist_thenReturnEmptyList() {
        var parentId = 1L;
        when(categoryRepository.findAllByParentId(parentId)).thenReturn(List.of());

        var actualResponse = categoryQueryService.getCategoriesByParent(parentId);

        assertTrue(actualResponse.isEmpty());
        verify(categoryRepository, times(1)).findAllByParentId(parentId);
        verify(categoryMapper, times(0)).toResponse(anyList(), anyBoolean());
    }

    @Test
    void getCategories_whenParentCategoriesExist_thenReturnCategories() {
        var categories = List.of(new CategoryEntity(1L, "Electronics", null, List.of()));
        var expectedResponse = List.of(new CategoryResponse(1L, "Electronics", null));
        when(categoryRepository.findAllByParentIsNull()).thenReturn(categories);
        when(categoryMapper.toResponse(categories, false)).thenReturn(expectedResponse);

        var actualResponse = categoryQueryService.getCategories();

        assertEquals(expectedResponse, actualResponse);
        verify(categoryRepository, times(1)).findAllByParentIsNull();
        verify(categoryMapper, times(1)).toResponse(categories, false);
    }

    @Test
    void getCategories_whenNoParentCategoriesExist_thenReturnEmptyList() {
        when(categoryRepository.findAllByParentIsNull()).thenReturn(List.of());

        var actualResponse = categoryQueryService.getCategories();

        assertTrue(actualResponse.isEmpty());
        verify(categoryRepository, times(1)).findAllByParentIsNull();
        verify(categoryMapper, times(0)).toResponse(anyList(), anyBoolean());
    }

}

