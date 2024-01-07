package com.example.demo.services;

import com.example.demo.dao.AuthorDAO;
import com.example.demo.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService{
    @Autowired
    private AuthorDAO authorDAO;

    @Override
    @Transactional
    public void saveAuthor(Author author) {
        authorDAO.saveAuthor(author);

    }

    @Override
    @Transactional
    public List<Author> getAuthors() {
        List<Author> authors = authorDAO.getAuthors();
        return authors;
    }

    @Override
    @Transactional
    public Author getAuthor(int id) {
        Author author = authorDAO.getAuthor(id);
        return author;
    }

    @Override
    @Transactional
    public void deleteAuthor(Author author) {
        authorDAO.deleteAuthor(author);
    }
}
