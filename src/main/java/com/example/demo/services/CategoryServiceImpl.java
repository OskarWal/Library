package com.example.demo.services;

import com.example.demo.dao.CategoryDAO;
import com.example.demo.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService
{
    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    @Transactional
    public void saveCategory(Category category)
    {
        categoryDAO.saveCategory(category);

    }

    @Override
    @Transactional
    public List<Category> getCategories()
    {
        List<Category> categories = categoryDAO.getCategories();
        return categories;
    }

    @Override
    @Transactional
    public Category getCategory(int categoryId) {
        return categoryDAO.getCategory(categoryId);
    }

    @Override
    @Transactional
    public void deleteCategory(Category category) {
        categoryDAO.deleteCategory(category);
    }
}
