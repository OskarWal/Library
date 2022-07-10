package com.example.demo.services;

import com.example.demo.entity.Kategoria;

import java.util.List;

public interface CategoryService {
    void saveCategory(Kategoria kategoria);

    List<Kategoria> getCategories();

    Kategoria getCategory(int categoryId);

    void deleteCategory(Kategoria category);
}
