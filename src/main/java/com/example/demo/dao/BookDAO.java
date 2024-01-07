package com.example.demo.dao;

import com.example.demo.entity.Book;

import java.util.List;

public interface BookDAO {
    List<Book> getBooks();
    void saveBook(Book book);
    Book getBookById(int id);

    void deleteBook(Book book);
}

