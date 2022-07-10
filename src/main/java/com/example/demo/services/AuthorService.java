package com.example.demo.services;

import com.example.demo.entity.Autor;


import java.util.List;

public interface AuthorService {
    void saveAuthor(Autor autor);
    List<Autor> getAuthors();
    Autor getAuthor(int id);

    void deleteAuthor(Autor author);
}
