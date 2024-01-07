package com.example.demo.dao;

import com.example.demo.entity.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorDAOImpl implements AuthorDAO {
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<Author> getAuthors()
    {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Author> query = currentSession.createQuery("from Author", Author.class);
        System.out.println(query.getResultList());
        List<Author> authors = query.getResultList();
        return authors ;
    }

    @Override
    public void saveAuthor(Author author)
    {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(author);
    }

    @Override
    public Author getAuthor(int id)
    {
        Session currentSession = sessionFactory.getCurrentSession();

        Author author =  (Author) currentSession.get(Author.class, id);
        return author ;
    }

    @Override
    public void deleteAuthor(Author author) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(author);
    }
}
