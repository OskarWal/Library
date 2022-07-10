package com.example.demo.dao;

import com.example.demo.entity.Ksiazka;

import java.util.List;

public interface BookDAO {
    List<Ksiazka> getBooks();
    void saveBook(Ksiazka ksiazka);
    Ksiazka getBookById(int id);

    void deleteBook(Ksiazka book);
}

