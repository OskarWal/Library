package com.example.demo.dao;


import com.example.demo.entity.Autor;

import java.util.List;

public interface AuthorDAO {
    public List<Autor> getAuthors();
    public void saveAuthor(Autor autor);

    Autor getAuthor(int id);

    void deleteAuthor(Autor autor);
}
