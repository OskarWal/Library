package com.example.demo.services;

import com.example.demo.entity.Ksiazka;

import java.util.List;

public interface BookService {
    void saveBook(Ksiazka ksiazka);

    List<Ksiazka> getBooks();
    Ksiazka getBookById(int id);

    void deleteBook(Ksiazka book);
}
