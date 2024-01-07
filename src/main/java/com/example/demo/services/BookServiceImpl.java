package com.example.demo.services;

import com.example.demo.dao.BookDAO;
import com.example.demo.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDAO bookDAO;

    @Override
    @Transactional
    public List<Book> getBooks() {
        List<Book> books = bookDAO.getBooks();
        return books;
    }

    @Override
    @Transactional
    public void saveBook(Book book) {
        bookDAO.saveBook(book);
    }

    @Override
    @Transactional
    public Book getBookById(int id)
    {
        Book book = bookDAO.getBookById(id);

        return book;
    }

    @Override
    @Transactional
    public void deleteBook(Book book) {
        bookDAO.deleteBook(book);
    }
}
