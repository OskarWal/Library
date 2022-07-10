package com.example.demo.dao;


import com.example.demo.entity.Kategoria;

import java.util.List;

public interface CategoryDAO {
    List<Kategoria> getCategories();
    void saveCategory(Kategoria kategoria);

    Kategoria getCategory(int categoryId);

    void deleteCategory(Kategoria category);
}
