package com.example.demo.dao;


import com.example.demo.entity.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> getCategories();
    void saveCategory(Category category);

    Category getCategory(int categoryId);

    void deleteCategory(Category category);
}
