package com.example.demo.services;

import com.example.demo.entity.Category;

import java.util.List;

public interface CategoryService {
    void saveCategory(Category category);

    List<Category> getCategories();

    Category getCategory(int categoryId);

    void deleteCategory(Category category);
}
