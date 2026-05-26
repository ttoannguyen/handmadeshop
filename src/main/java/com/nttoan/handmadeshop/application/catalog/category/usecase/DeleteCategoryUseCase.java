package com.nttoan.handmadeshop.application.catalog.category.usecase;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.nttoan.handmadeshop.application.common.dto.BaseResponse;
import com.nttoan.handmadeshop.domain.catalog.category.entity.Category;
import com.nttoan.handmadeshop.domain.catalog.category.repository.CategoryRepository;

@Service
public class DeleteCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public DeleteCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public BaseResponse<Void> execute(String id) {
        try {
            Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Category not found"));

            category.deactivate();
            categoryRepository.save(category);

            return BaseResponse.success(null);
        } catch (NoSuchElementException e) {
            return BaseResponse.notFound(e.getMessage());
        } catch (Exception e) {
            return BaseResponse.error("ERROR", "Failed to delete category", 500, e.getMessage());
        }
    }
}
