package com.example.demo.services;

import com.example.demo.entity.Author;


import java.util.List;

public interface AuthorService {
    void saveAuthor(Author author);
    List<Author> getAuthors();
    Author getAuthor(int id);

    void deleteAuthor(Author author);
}
