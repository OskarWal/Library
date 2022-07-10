package com.example.demo.services;

import com.example.demo.dao.AuthorDAO;
import com.example.demo.entity.Autor;
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
    public void saveAuthor(Autor autor) {
        authorDAO.saveAuthor(autor);

    }

    @Override
    @Transactional
    public List<Autor> getAuthors() {
        List<Autor> authors = authorDAO.getAuthors();
        return authors;
    }

    @Override
    @Transactional
    public Autor getAuthor(int id) {
        Autor author = authorDAO.getAuthor(id);
        return author;
    }

    @Override
    @Transactional
    public void deleteAuthor(Autor autor) {
        authorDAO.deleteAuthor(autor);
    }
}
