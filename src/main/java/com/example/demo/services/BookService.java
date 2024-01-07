package com.example.demo.services;

import com.example.demo.entity.Book;

import java.util.List;

public interface BookService {
    void saveBook(Book book);

    List<Book> getBooks();
    Book getBookById(int id);

    void deleteBook(Book book);
}
