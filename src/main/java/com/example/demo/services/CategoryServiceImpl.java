package com.example.demo.services;

import com.example.demo.dao.CategoryDAO;
import com.example.demo.entity.Kategoria;
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
    public void saveCategory(Kategoria kategoria)
    {
        categoryDAO.saveCategory(kategoria);

    }

    @Override
    @Transactional
    public List<Kategoria> getCategories()
    {
        List<Kategoria> categories = categoryDAO.getCategories();
        return categories;
    }

    @Override
    @Transactional
    public Kategoria getCategory(int categoryId) {
        return categoryDAO.getCategory(categoryId);
    }

    @Override
    @Transactional
    public void deleteCategory(Kategoria category) {
        categoryDAO.deleteCategory(category);
    }
}
