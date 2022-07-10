package com.example.demo.dao;


import com.example.demo.entity.Ksiazka;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Ksiazka> getBooks() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Ksiazka> query = currentSession.createQuery("from Ksiazka ksiazka order by ksiazka.id desc", Ksiazka.class);
        List<Ksiazka> books = query.getResultList();


        return books ;
    }
    @Override
    public void saveBook(Ksiazka ksiazka) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(ksiazka);
    }

    @Override
    public Ksiazka getBookById(int id) {
        Session currentSession = sessionFactory.getCurrentSession();

        Ksiazka book =  (Ksiazka) currentSession.get(Ksiazka.class, id);

        return book ;
    }

    @Override
    public void deleteBook(Ksiazka book) {
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.delete(book);

    }
}
