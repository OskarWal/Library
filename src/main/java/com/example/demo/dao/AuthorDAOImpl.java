package com.example.demo.dao;

import com.example.demo.entity.Autor;
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
    public List<Autor> getAuthors()
    {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Autor> query = currentSession.createQuery("from Autor", Autor.class);
        System.out.println(query.getResultList());
        List<Autor> authors = query.getResultList();
        return authors ;
    }

    @Override
    public void saveAuthor(Autor autor)
    {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(autor);
    }

    @Override
    public Autor getAuthor(int id)
    {
        Session currentSession = sessionFactory.getCurrentSession();

        Autor author =  (Autor) currentSession.get(Autor.class, id);
        return author ;
    }

    @Override
    public void deleteAuthor(Autor autor) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(autor);
    }
}
