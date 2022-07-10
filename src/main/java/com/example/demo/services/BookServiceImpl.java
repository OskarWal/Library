package com.example.demo.services;

import com.example.demo.dao.BookDAO;
import com.example.demo.entity.Ksiazka;
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
    public List<Ksiazka> getBooks() {
        List<Ksiazka> books = bookDAO.getBooks();
        return books;
    }

    @Override
    @Transactional
    public void saveBook(Ksiazka ksiazka) {
        bookDAO.saveBook(ksiazka);
    }

    @Override
    @Transactional
    public Ksiazka getBookById(int id)
    {
        Ksiazka book = bookDAO.getBookById(id);

        return book;
    }

    @Override
    @Transactional
    public void deleteBook(Ksiazka book) {
        bookDAO.deleteBook(book);
    }
}
