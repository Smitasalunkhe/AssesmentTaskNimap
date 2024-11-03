package com.assesment.nimap1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.assesment.nimap1.entity.Category;
import com.assesment.nimap1.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Category> getAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    public Category updateCategory(Long id, Category categoryDetails) {
        Category updatedCategory = getCategoryById(id);
        updatedCategory.setCategoryName(categoryDetails.getCategoryName());
        updatedCategory.setDescription(categoryDetails.getDescription());
        return categoryRepository.save(updatedCategory);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
