package com.example.demo.dao;


import com.example.demo.entity.Author;

import java.util.List;

public interface AuthorDAO {
    public List<Author> getAuthors();
    public void saveAuthor(Author author);

    Author getAuthor(int id);

    void deleteAuthor(Author author);
}
